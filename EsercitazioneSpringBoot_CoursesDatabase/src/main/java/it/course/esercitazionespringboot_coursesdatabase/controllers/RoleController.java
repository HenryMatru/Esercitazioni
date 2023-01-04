package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.services.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService,
                          UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = (ArrayList<Role>) this.roleService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Role> insertRole(@RequestBody Role newRole){
        for (Role role : this.roleService.getRoles()) {
            if (newRole.getName().equals(role.getName())) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        Role role = this.roleService.insertRole(newRole);
        return new ResponseEntity<Role>(role,HttpStatus.OK);
    }

    @PostMapping("/insert/{id}/{idUser}")
    public ResponseEntity<Role> insertUserIntoRole(@PathVariable("id") Integer id,
                                                   @PathVariable("idUser") Integer idUser){
        if (this.roleService.getRole(id) == null || this.userService.getUser(idUser) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Role role = this.roleService.getRole(id).get();
        User user = this.userService.getUser(idUser).get();
        Set<User> newUsers = role.getUsers();
        newUsers.add(user);
        role.setUsers(newUsers);
        return new ResponseEntity<Role>(role,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRoles(@PathVariable("id") Integer id, @RequestBody Role modifiedRole) {
        Role role = this.roleService.getRole(id).get();
        role.setName(modifiedRole.getName());
        return new ResponseEntity<>(this.roleService.insertRole(role), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") Integer id){
        this.roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
