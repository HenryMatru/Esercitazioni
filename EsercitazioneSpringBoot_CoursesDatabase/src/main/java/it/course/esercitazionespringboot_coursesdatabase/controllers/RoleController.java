package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.business.UserService;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.payload.response.MessageResponse;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/apiRole")
public class RoleController {

    private final RoleService roleService;

    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService,
                          UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/roles")
    public ResponseEntity<ArrayList<Role>> getRoles(){
        ArrayList<Role> roles = (ArrayList<Role>) this.roleService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<?> createRole(@RequestBody Role newRole){
        if (((ArrayList<Role>)this.roleService.getRoles()).contains(newRole)) {
            return new ResponseEntity<>(new MessageResponse("This user has already this role!"), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(this.roleService.insertRole(newRole), HttpStatus.OK);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Role> updateRoles(@PathVariable("id") Integer id, @RequestBody Role modifiedRole) {
        Role role = this.roleService.getRole(id).get();
        role.setName(modifiedRole.getName());
        return new ResponseEntity<>(this.roleService.insertRole(role), HttpStatus.OK);
    }

    @DeleteMapping("/role/associate/{idRole}")
    public  ResponseEntity<HttpStatus> associateUserToRole(@PathVariable Integer id) {
       // TODO
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<HttpStatus> deleteRole(@PathVariable Integer id) {
        for (User user : this.userService.getUsers()) {
            if (user.getRoles().contains(this.roleService.getRole(id).get())) {
                user.getRoles().remove(this.roleService.getRole(id).get());
            }
        }
        this.roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*

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
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Integer id) {
        Role role = this.roleService.getRole(id).get();
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Role> insertRole(@RequestBody Role newRole){
        for (Role role : this.roleService.getRoles()) {
            if (newRole.getName().equals(role.getName())) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        Role role = this.roleService.insertRole(newRole);
        return new ResponseEntity<Role>(role,HttpStatus.CREATED);
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
     */
}
