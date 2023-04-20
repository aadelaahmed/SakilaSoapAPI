package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.response.ResponseMessage;
import org.example.dto.CategoryDto;
import org.example.mapper.CategoryMapper;
import org.example.repository.CategoryRepository;
import org.example.service.category.CategoryService;
import org.example.service.category.CategoryServiceImpl;

import java.util.List;

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@WebService(name = "CategoryService")
public class CategoryController {

    private final CategoryServiceImpl categoryService = new CategoryServiceImpl(
            new CategoryRepository(), CategoryMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllCategories")
    @WebResult(name = "categories")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAll();
    }

    @WebMethod(operationName = "getCategoryById")
    @WebResult(name = "category")
    public CategoryDto getCategoryById(@WebParam(name = "id") Integer id) {
        CategoryDto categoryDto = categoryService.getById(id);
        if (categoryDto == null) {
            throw new WebServiceException("Category with id " + id + " not found");
        }
        return categoryDto;
    }

    @WebMethod(operationName = "createCategory")
    @WebResult(name = "categoryAdded")
    public CategoryDto createCategory(@WebParam(name = "category") CategoryDto categoryDto) {
        CategoryDto createdCategoryDto = categoryService.create(categoryDto, categoryDto.getId());
        if (createdCategoryDto != null) {
            return createdCategoryDto;
        }
        throw new WebServiceException("Can't create category");
    }

    @WebMethod(operationName = "updateCategory")
    @WebResult(name = "categoryUpdated")
    public ResponseMessage updateCategory(@WebParam(name = "id") Integer id, @WebParam(name = "category") CategoryDto categoryDto) {
        boolean res = categoryService.update(id, categoryDto);
        if (res) {
            return new ResponseMessage("Category was updated successfully");
        } else {
            throw new WebServiceException("Failed to update category");
        }
    }

    @WebMethod(operationName = "deleteCategoryById")
    @WebResult(name = "categoryDeleted")
    public ResponseMessage deleteCategoryById(@WebParam(name = "id") Integer id) {
        categoryService.deleteById(id);
        return new ResponseMessage("Category was deleted successfully");
    }

    @WebMethod(operationName = "addFilmToCategory")
    @WebResult(name = "filmAdded")
    public ResponseMessage addFilmToCategory(@WebParam(name = "categoryId") Integer categoryId, @WebParam(name = "filmId") Integer filmId) {
        boolean isAdded = categoryService.addFilmToCategory(filmId, categoryId);
        if (isAdded) {
            return new ResponseMessage("The film was added successfully to the category with id: " + categoryId);
        } else {
            throw new WebServiceException("Failed to add film to category");
        }
    }
}
