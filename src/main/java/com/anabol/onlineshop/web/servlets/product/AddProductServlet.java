package com.anabol.onlineshop.web.servlets.product;

import com.anabol.onlineshop.entity.Product;
import com.anabol.onlineshop.service.ProductService;
import com.anabol.onlineshop.web.auth.TokenUtils;
import com.anabol.onlineshop.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddProductServlet extends HttpServlet {
    private ProductService productService;
    private List<String> tokens;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("add.html", null));
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        product.setPrice(Double.valueOf(request.getParameter("price")));
        productService.insert(product);

        response.sendRedirect(request.getContextPath() + "/products");

    }

    public AddProductServlet(ProductService productService, List<String> tokens) {
        this.productService = productService;
        this.tokens = tokens;
    }
}
