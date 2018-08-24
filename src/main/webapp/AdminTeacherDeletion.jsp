<%@ page import="model.Teacher" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.TeacherDao" %>
<%@ page import="dao.AdminDao" %>
<%@ page import="model.Admin" %>
<%@ page import="org.apache.commons.lang.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 11-06-2018
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%


    if (session.getAttribute("adminuser") != null && session.getAttribute("password") != null)
    {
        try
        {
            String[] checkedid = request.getParameterValues("id2");

            ArrayList<Teacher> teacherids = new ArrayList<>();

            for (int i = 0; i < checkedid.length; i++)
            {
                Teacher teacher = new Teacher();
                teacher.setTeacherid(Integer.parseInt(checkedid[i]));

                teacherids.add(teacher);
            }

            TeacherDao teacherDao = new TeacherDao();
            boolean check = teacherDao.deleteTeacher(teacherids);

            if (check == true)
            {
                /*Used Admin Object as the the method was already defined to get teacher info in admindao*/
                int adminid = Integer.parseInt(session.getAttribute("sessionadminid").toString());
                Admin admin = new Admin();
                admin.setAdminid(adminid);

                ArrayList<Teacher> teacherinfo;

                AdminDao adminDao = new AdminDao();
                teacherinfo = adminDao.getTeacherInfo(admin);

                for (int i = 0; i < teacherinfo.size(); i++)
                {
%>

<tr>
    <th>
        <div class="pretty p-icon p-curve p-has-indeterminate">
            <input type="checkbox" class="checkitem" value="<%= teacherinfo.get(i).getTeacherid() %>" name="<%= teacherinfo.get(i).getTeacherid() %>"/>
            <div class="state">
                <i class="icon mdi mdi-check"></i>
                <label>Check</label>
            </div>
            <div class="state p-is-indeterminate">
                <i class="icon mdi mdi-minus"></i>
                <label>Indeterminate</label>
            </div>
        </div>
    </th>
    <th scope="row"><%= teacherinfo.get(i).getTeacherid() %></th>
    <td><%= StringUtils.capitalize(teacherinfo.get(i).getTeacherfirstname()) %></td>
    <td><%= StringUtils.capitalize(teacherinfo.get(i).getTeacherlastname()) %></td>
    <td><%= StringUtils.capitalize(teacherinfo.get(i).getTeachercoursename()) %></td>

</tr>
<%
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendRedirect("AdminUnknownError.jsp");
        }


    }
    else
    {
        response.sendRedirect("AdminLogin.jsp");
    }

%>

