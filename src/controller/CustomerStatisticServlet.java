package controller;

import dao.CustomerStatisticDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerStatistic5;

@WebServlet("/CustomerStatisticServlet")
public class CustomerStatisticServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        String action = req.getParameter("action");
        if ("sort".equals(action)) {
            ArrayList<CustomerStatistic5> listCS = (ArrayList<CustomerStatistic5>) session.getAttribute("listCS");
            CustomerStatistic5 guestCS = (CustomerStatistic5) session.getAttribute("guestCS");
            Boolean sortAsc = (Boolean) session.getAttribute("sortAsc");
            sortAsc = (sortAsc == null) ? true : !sortAsc;
            session.setAttribute("sortAsc", sortAsc);

            sortByRevenue(listCS, guestCS, sortAsc);
            req.getRequestDispatcher("customerStatistic.jsp").forward(req, resp);
            return;
        }

        String statisticType = req.getParameter("statisticType");
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");

        if (startDateStr == null || endDateStr == null || startDateStr.isEmpty() || endDateStr.isEmpty()) {
            req.setAttribute("errorMsg", "Ngày kết thúc phải sau ngày bắt đầu");
            req.getRequestDispatcher("selectStatisticType.jsp").forward(req, resp);
            return;
        }

        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);

        if (endDate.before(startDate)) {
            req.setAttribute("errorMsg", "Ngày kết thúc phải sau ngày bắt đầu");
            req.getRequestDispatcher("selectStatisticType.jsp").forward(req, resp);
            return;
        }

        CustomerStatisticDAO dao = new CustomerStatisticDAO();
        ArrayList<CustomerStatistic5> listCS = dao.getStatisticByDate(startDate, endDate);
        CustomerStatistic5 guestCS = dao.getGuestStatistic(startDate, endDate);

        float totalRevenueAll = calcTotalAndPercent(listCS, guestCS);

        session.setAttribute("statisticType", statisticType != null ? statisticType : "Thống kê khách hàng theo doanh thu");
        session.setAttribute("startDate", startDateStr);
        session.setAttribute("endDate", endDateStr);
        session.setAttribute("listCS", listCS);
        session.setAttribute("guestCS", guestCS);
        session.setAttribute("totalRevenueAll", totalRevenueAll);
        session.setAttribute("sortAsc", false);

        req.getRequestDispatcher("customerStatistic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected float calcTotalAndPercent(ArrayList<CustomerStatistic5> listCS, CustomerStatistic5 guestCS) {
        float totalAll = 0;
        for (CustomerStatistic5 cs : listCS) {
            totalAll += cs.getTotalRevenue();
        }
        if (guestCS != null) {
            totalAll += guestCS.getTotalRevenue();
        }

        if (totalAll > 0) {
            for (CustomerStatistic5 cs : listCS) {
                cs.setPercentage((cs.getTotalRevenue() / totalAll) * 100);
            }
            if (guestCS != null) {
                guestCS.setPercentage((guestCS.getTotalRevenue() / totalAll) * 100);
            }
        }
        return totalAll;
    }

    protected void sortByRevenue(ArrayList<CustomerStatistic5> listCS, CustomerStatistic5 guestCS, boolean ascending) {
        if (listCS == null) return;
        Collections.sort(listCS, new Comparator<CustomerStatistic5>() {
            @Override
            public int compare(CustomerStatistic5 o1, CustomerStatistic5 o2) {
                return ascending
                        ? Float.compare(o1.getTotalRevenue(), o2.getTotalRevenue())
                        : Float.compare(o2.getTotalRevenue(), o1.getTotalRevenue());
            }
        });
    }
}