package Controller;

import Model.ProductStructerToView;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class carController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //讀取首頁,所選擇要做的事情
            String operation = request.getParameter("operation");
            //預設轉發物件為空
            RequestDispatcher rd = null;
            HttpSession session = request.getSession();
            //           String user = request.getParameter("user");

            List<String[]> list = listcar();
            request.setAttribute("list", list);
            //request.setAttribute("list", getProductStructerToView());
            //           if("car".equals(operation)){
            rd = request.getRequestDispatcher("confirmBuy.jsp");
            rd.forward(request, response);
        }

    }
//            
//            //取得購物車陣列
//            List<String> list = listcar();
//            
//            //將陣列放request櫃台
//            request.setAttribute("list", list);
//            //轉發
//            RequestDispatcher rd2 = request.getRequestDispatcher("confirmBuy1.jsp");
//            rd2.forward(request, response);
//        }
//    

    //宣告回傳一個陣列
    private List<String[]> listcar() {
        //測試用
        System.out.println("----------------------------------------------------------" + new java.util.Date());
        List<String[]> list = new ArrayList<>();

        String url = "jdbc:sqlserver://172.16.168.210:1433;databaseName=member1;";
        String user = "sa";
        String pass = "12345";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //載入驅動程式
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //建立連線物件
            con = DriverManager.getConnection(url, user, pass);
            //建立SQL陳述句物件
            stmt = con.createStatement();
            //要叫SQL做的事
            String query = "select * from car"; //撈出所有購物車內容
            //執行SQL查詢,獲取結果
            rs = stmt.executeQuery(query);

            //逐筆讀取所查詢結果
            while (rs.next()) {
                String[] stringArray = new String[4];
                stringArray[0] = rs.getString("產品編號");
                stringArray[1] = rs.getString("產品名稱");
                stringArray[2] = Integer.toString(rs.getInt("產品單價"));
                stringArray[3] = Integer.toString(rs.getInt("產品數量"));
                
                list.add(stringArray);
                
                
                
                
                

//ProductStructerToView p = new ProductStructerToView();
//                p.set產品編號(rs.getString("產品編號"));
//                p.set產品名稱(rs.getString("產品名稱"));
//                p.set產品單價(rs.getInt("產品單價"));
//                p.set產品數量(rs.getInt("產品數量"));
                //p.set產品總價(rs.getInt("產品總價"));
                ////////////////////////////////////////////////////
                //int 產品加入順序 = rs.getInt("產品加入順序");
                //String 會員編號 = rs.getString("會員編號");
//                String 產品編號 = rs.getString("產品編號");
//                String 產品名稱 = rs.getString("產品名稱");
//                int 產品單價 = rs.getInt("產品單價");
//                int 產品數量 = rs.getInt("產品數量");
                //int 產品總價 = rs.getInt("產品總價");
                //String s = 會員編號+ " " + 產品編號 + " " + 產品名稱 + " "
                // + 產品單價 + " " + 產品數量 + " " + 產品總價;
//                String s1 = 產品編號 ;
//                String s2 = 產品名稱 ;
//                int s3 = 產品單價 ;
//                int s4 = 產品數量 ;
//                int s5 = 產品單價*產品數量 ;
//                
                //測試用
                //System.out.println(s);
                //list.add(s);
                //list.add(p);
//                list.add(s1);
//                list.add(s2);
//                list.add(s3);
//                list.add(s4);
//                list.add(s5);
//                
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }

        }
        return list;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
