package controller;

import dao.DepartmentDao;
import dao.StaffDao;
import model.Department;
import model.Staff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/staff")
public class StaffServlet extends HttpServlet {
    StaffDao staffDao = new StaffDao();
    DepartmentDao departmentDao = new DepartmentDao();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            default:
                show(req, resp);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "create":
                creatpost(request,resp);
                break;
            case "edit":
                editpost(request,resp);
                break;
            case "delete":
                deletepost(request,resp);
                break;
        }
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("department", departmentDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        req.setAttribute("staffs", staffDao.getAllByName(search));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("staffs", staffDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Staff staff = staffDao.findById(id);
        List<Department> department = departmentDao.getAll();
        req.setAttribute("staff",staff );
        req.setAttribute("department",department);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/delete.jsp");
        dispatcher.forward(req, resp);
    }
    private void deletepost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Staff> students = staffDao.getAll();
        int id = Integer.parseInt(req.getParameter("id"));
        staffDao.delete(id);
        req.setAttribute("department", students);
        resp.sendRedirect("/staff");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Staff staff = staffDao.findById(id);
        List<Department> department = departmentDao.getAll();
        req.setAttribute("staff",staff );
        req.setAttribute("department",department);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(req, resp);
    }

    private void creatpost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int id = staffDao.getAll().size() + 1;
        String name = request.getParameter("name");
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int idDepartment = Integer.parseInt(request.getParameter("class"));

        Staff st = new Staff(id, name, birth, address, phone, email, departmentDao.findById(idDepartment));
        staffDao.create(st);
        resp.sendRedirect("/staff");
    }
    private void editpost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int ide = Integer.parseInt(request.getParameter("id"));
        String namee = request.getParameter("name");
        LocalDate birthe = LocalDate.parse(request.getParameter("birth"));
        String addresse = request.getParameter("address");
        String phonee = request.getParameter("phone");
        String emaile = request.getParameter("email");
        int idClasse = Integer.parseInt(request.getParameter("class"));

        Staff ste = new Staff(ide, namee, birthe, addresse, phonee, emaile, departmentDao.findById(idClasse));
        staffDao.edit(ide, ste);
        resp.sendRedirect("/staff");
    }
}




