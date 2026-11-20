package controller;

import dao.InvoiceDAO;
import dao.TicketDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer5;
import model.Invoice5;
import model.Ticket5;

@WebServlet("/TransactionDetailServlet")
public class TransactionDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        int customerId = Integer.parseInt(req.getParameter("customerId"));
        String startDateStr = (String) session.getAttribute("startDate");
        String endDateStr = (String) session.getAttribute("endDate");

        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);

        InvoiceDAO invoiceDAO = new InvoiceDAO();
        TicketDAO ticketDAO = new TicketDAO();

        Customer5 customer;
        ArrayList<Invoice5> listInvoice;

        if (customerId > 0) {
            customer = invoiceDAO.getCustomerById(customerId);
            listInvoice = invoiceDAO.getInvoicesByCustomer(customerId, startDate, endDate);
        } else {
            customer = new Customer5();
            customer.setId(0);
            customer.setCustomerCode("GUEST");
            customer.setFullName("Khách vãng lai");
            listInvoice = invoiceDAO.getGuestInvoices(startDate, endDate);
        }

        for (Invoice5 inv : listInvoice) {
            ArrayList<Ticket5> tickets = ticketDAO.getTicketsByInvoice(inv.getId());
            inv.setTickets(tickets);
        }

        req.setAttribute("customer", customer);
        req.setAttribute("listInvoice", listInvoice);
        req.getRequestDispatcher("transactionDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}