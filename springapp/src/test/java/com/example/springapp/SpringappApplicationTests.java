package com.example.springapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


// import com.example.springapp.model.Tree;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
	private  MockMvc mockMvc ;
	@Test
void testAddNew() throws Exception {
    String st = "{\"doorId\": 1001,\"location\": \"Test Recipe\", \"accessCode\": \"Ingredient 1\", \"accessType\": \"Step 1\"}";
    mockMvc.perform(MockMvcRequestBuilders.post("/doors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(st)
            .accept(MediaType.APPLICATION_JSON))
       .andExpect(MockMvcResultMatchers.status().isOk())
       .andExpect(jsonPath("$").value(true))
       .andReturn();
}


@Test
void testGetOne() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/doors/1001")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.accessType").value("Step 1")) // Adjust the JSON path
            .andReturn();
}

@Test
	void testByType() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.get("/doors/by-access-type/step 1")
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").isArray())
						.andReturn();
	}

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/doors")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

	@Test 
    public void testControllerFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/controller"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
    
	@Test 
    public void testModelFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/model"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
	@Test 
    public void testRepositoryFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/repository"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
	@Test 
   
	public void testServiceFolder() { 
        String directoryPath = "src/main/java/com/example/springapp/service"; // Replace with the path to your directory 
        File directory = new File(directoryPath); 
        assertTrue(directory.exists() && directory.isDirectory()); 
    }
    
	@Test
    public void testControllerClassExists() {
        checkClassExists("com.example.springapp.controller.DoorController");
    }

    @Test
    public void testRepositoryClassExists() {
        checkClassExists("com.example.springapp.repository.DoorRepository");
    }

    @Test
    public void testServiceClassExists() {
        checkClassExists("com.example.springapp.service.DoorService");
    }

    @Test
    public void testModelClassExists() {
        checkClassExists("com.example.springapp.model.Door");
    }

    @Test
    public void testControllerHasAutowiredField() {
        checkFieldExists("com.example.springapp.controller.DoorController", "doorService");
    }

    @Test
    public void testModelHasDoortIdField() {
        checkFieldExists("com.example.springapp.model.Door", "doorId");
    }

    @Test
    public void testModelHasLocationField() {
        checkFieldExists("com.example.springapp.model.Door", "location");
    }

    @Test
    public void testModelHasAccessCodeField() {
        checkFieldExists("com.example.springapp.model.Door", "accessCode");
    }

    @Test
    public void testModelHasAccessTypeField() {
        checkFieldExists("com.example.springapp.model.Door", "accessType");
    }

    

    @Test
    public void testRepositoryExtendsJpaRepository() {
        checkClassImplementsInterface("com.example.springapp.repository.DoorRepository", "org.springframework.data.jpa.repository.JpaRepository");
    }

    @Test
    public void testServiceHasAutowiredField() {
        checkFieldExists("com.example.springapp.service.DoorService", "doorRepository");
    }

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interfaceClazz = Class.forName(interfaceName);
            assertTrue(interfaceClazz.isAssignableFrom(clazz));
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " or interface " + interfaceName + " does not exist.");
        }
    }}
	

	
	

