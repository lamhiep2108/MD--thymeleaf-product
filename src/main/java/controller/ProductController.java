package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.IProductService;
import service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private  final IProductService productService=new ProductService();
    @GetMapping("")
    public String index(Model model){
        List<Product> productList= productService.findAll();
        model.addAttribute("products",productList);
        return "/index";
    }
    @GetMapping("/create")
    public String cteate(Model model){
        model.addAttribute("product",new Product());
        return "/create";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id,Model model){
        model.addAttribute("product",productService.findById(id));
        return "/edit";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id,Model model){
        model.addAttribute("product",productService.findById(id));
        return "/delete";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id,Model model){
        model.addAttribute("product",productService.findById(id));
        return "/view";
    }
    @PostMapping("/search")
    public String search(@RequestParam String name ,Model model){
        model.addAttribute("product",productService.findByName(name));
        return "/index";
    }


        @PostMapping("/save")
    public String save(Product product){
        product.setId((int) (Math.random()*10000));
        productService.save(product);
        return "redirect:/product";
        }
    @PostMapping("/update")
    public String update(Product product){
        productService.update(product.getId(),product);
        return "redirect:/product";
    }
    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect){
        productService.remove(product.getId());
        redirect.addFlashAttribute("success","remove product cussessfully");
        return "redirect:/product";
    }
}
