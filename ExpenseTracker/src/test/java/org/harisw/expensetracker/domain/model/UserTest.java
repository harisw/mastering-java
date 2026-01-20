/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package org.harisw.expensetracker.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Administrator
 */
public class UserTest {

    /**
     * Test of getId method, of class User.
     */
    @org.junit.Test
    public void testGetId() {
        System.out.println("getId");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        UUID result = instance.getId();
        assertNotNull(result);
    }

    /**
     * Test of getName method, of class User.
     */
    @org.junit.Test
    public void testGetName() {
        System.out.println("getName");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        String result = instance.getName();
        assertEquals("John Doe", result);
    }

    /**
     * Test of setName method, of class User.
     */
    @org.junit.Test
    public void testSetName() {
        System.out.println("setName");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        instance.setName("Jane Doe");
        assertEquals("Jane Doe", instance.getName());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @org.junit.Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        String result = instance.getEmail();
        assertEquals("john.doe@example.com", result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @org.junit.Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        String result = instance.getPassword();
        assertEquals("password123", result);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @org.junit.Test
    public void testSetPassword() {
        System.out.println("setPassword");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        instance.setPassword("newpassword456");
        assertEquals("newpassword456", instance.getPassword());
    }

    /**
     * Test of getCreatedAt method, of class User.
     */
    @org.junit.Test
    public void testGetCreatedAt() {
        System.out.println("getCreatedAt");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        LocalDateTime result = instance.getCreatedAt();
        assertNotNull(result);
    }

    /**
     * Test of getUpdatedAt method, of class User.
     */
    @org.junit.Test
    public void testGetUpdatedAt() {
        System.out.println("getUpdatedAt");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        LocalDateTime result = instance.getUpdatedAt();
        assertNotNull(result);
    }

    /**
     * Test of setUpdatedAt method, of class User.
     */
    @org.junit.Test
    public void testSetUpdatedAt() {
        System.out.println("setUpdatedAt");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        LocalDateTime newUpdatedAt = LocalDateTime.now();
        instance.setUpdatedAt(newUpdatedAt);
        assertEquals(newUpdatedAt, instance.getUpdatedAt());
    }

    /**
     * Test of hashCode method, of class User.
     */
    @org.junit.Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        int result = instance.hashCode();
        assertNotNull(result);
    }

    /**
     * Test of toString method, of class User.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User("John Doe", "john.doe@example.com", "password123");
        String result = instance.toString();
        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("john.doe@example.com"));
    }
}