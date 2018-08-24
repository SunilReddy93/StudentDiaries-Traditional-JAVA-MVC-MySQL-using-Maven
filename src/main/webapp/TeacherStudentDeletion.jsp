<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.StudentDao" %>
<%@ page import="model.Teacher" %>
<%@ page import="org.apache.commons.lang.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: Sunil Reddy
  Date: 12-06-2018
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    if (session.getAttribute("teacheruser") != null && session.getAttribute("teacherpassword") != null)
    {
        try
        {
            String[] checkedid = request.getParameterValues("id");

            ArrayList<Student> studentids = new ArrayList<>();

            for (int i = 0; i < checkedid.length; i++)
            {
                Student student = new Student();
                student.setStudentid(Integer.parseInt(checkedid[i]));

                studentids.add(student);
            }

            StudentDao studentDao = new StudentDao();
            boolean check = studentDao.deleteStudent(studentids);

            if (check == true)
            {
                int teacherid = Integer.parseInt(session.getAttribute("sessionteacherid").toString());

                Teacher teacher = new Teacher();
                teacher.setTeacherid(teacherid);

                ArrayList<Student> studentArrayList;

                studentArrayList = studentDao.getStudentInfo(teacher);

                for (int i = 0; i < studentArrayList.size(); i++)
                {

%>

<tr>
    <th>
        <div class="pretty p-icon p-curve p-has-indeterminate">
            <input type="checkbox" class="checkitem" value="<%= studentArrayList.get(i).getStudentid() %>" name="<%= studentArrayList.get(i).getStudentid() %>"/>
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
    <th scope="row"><%= studentArrayList.get(i).getStudentrollno() %></th>
    <th><%= studentArrayList.get(i).getStudentid() %></th>
    <td><%= StringUtils.capitalize(studentArrayList.get(i).getStudentfirstname()) %></td>
    <td><%= StringUtils.capitalize(studentArrayList.get(i).getStudentlastname()) %></td>
    <td><%= StringUtils.capitalize(studentArrayList.get(i).getStudentcoursename()) %></td>

</tr>
<%
                    }
                }
                else
                {
                    response.sendRedirect("TeacherUnknownError.jsp");
                }


        }
        catch (Exception e)
        {
            e.printStackTrace();
            response.sendRedirect("TeacherUnknownError.jsp");
        }
    }
    else
    {
        response.sendRedirect("TeacherLogin.jsp");
    }


%>

