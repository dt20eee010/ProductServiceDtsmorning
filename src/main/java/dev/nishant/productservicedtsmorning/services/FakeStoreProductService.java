package dev.nishant.productservicedtsmorning.services;
import dev.nishant.productservicedtsmorning.dtos.ErrorDto;
import dev.nishant.productservicedtsmorning.dtos.FakeStoreProductDtos;
import dev.nishant.productservicedtsmorning.dtos.UpdateProductDtos;
import dev.nishant.productservicedtsmorning.exception.ProductNotFoundExceptions;
import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){

        this.restTemplate=restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundExceptions {
        ResponseEntity<FakeStoreProductDtos> fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId, FakeStoreProductDtos.class);

        FakeStoreProductDtos fakestoreproduct = fakeStoreProductDtos.getBody();
        if(fakestoreproduct==null)
        {
            throw new ProductNotFoundExceptions("product with id " + productId + " does not exsist!!!!!! retry");

        }
        return fakestoreproduct.toProduct();

    }

    @Override
    public Product createProduct(String title,double price,String description,String image,String category) {
        FakeStoreProductDtos fakeStoreProductDto = new FakeStoreProductDtos();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        FakeStoreProductDtos response = restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto,FakeStoreProductDtos.class) ;
        return response.toProduct();
    }
    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundExceptions {
        Map<String,String> params = new HashMap<>();
        params.put("id",productId.toString());
        Product product = getSingleProduct(productId);
        restTemplate.delete("https://fakestoreapi.com/products/"+productId,params);
        return product;
    }

    @Override
    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> listproduct= new ArrayList<>();
        FakeStoreProductDtos []fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDtos[].class);
        for(FakeStoreProductDtos fakeStoreProductDto :fakeStoreProductDtos)
        {
            FakeStoreProductDtos tempfakestore = new FakeStoreProductDtos();
            tempfakestore.setId(fakeStoreProductDto.getId());
            tempfakestore.setImage(fakeStoreProductDto.getImage());
            tempfakestore.setDescription(fakeStoreProductDto.getDescription());
            tempfakestore.setPrice(fakeStoreProductDto.getPrice());
            tempfakestore.setCategory(fakeStoreProductDto.getCategory());
            listproduct.add(tempfakestore.toProduct());
        }
        return listproduct;

    }

    @Override
    public List<Category> getCategory() {
        List<String> fakeStoreCategoryDtolist = restTemplate.getForObject("https://fakestoreapi.com/products/categories", List.class);
        List<Category> CategoryList = new ArrayList<>();
        for( String fakestorecategory :fakeStoreCategoryDtolist)
        {
            Category tempcategory = new Category();
            tempcategory.setTitle(fakestorecategory);
            CategoryList.add(tempcategory);
        }
        return  CategoryList;
    }
    @Override
    public List<Product> getProduct(String Category){
        List<Product> listProduct = new ArrayList<Product>();
        List<LinkedHashMap<String,String>> ListFakeProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+Category,List.class);
        for(LinkedHashMap<String,String> linkedhashmap : ListFakeProductDtos)
        {
            Product tempproduct = new Product();
            tempproduct.setId(Long.parseLong(String.valueOf(linkedhashmap.get("id"))));
            tempproduct.setDescription(linkedhashmap.get("description"));
            tempproduct.setTitle(linkedhashmap.get("title"));
            tempproduct.setPrice(Double.parseDouble(String.valueOf(linkedhashmap.get("price"))));
            Category tempCategory = new Category();
            tempCategory.setTitle(linkedhashmap.get("Category"));
            tempproduct.setCategory(tempCategory);
            tempproduct.setImageUrl(linkedhashmap.get("image"));
            listProduct.add(tempproduct);
        }
        return listProduct;
    }

    @Override
    public Product patchProduct(Long productId, String title, double price, String description, String image, String category) {
        return null;
    }


}
