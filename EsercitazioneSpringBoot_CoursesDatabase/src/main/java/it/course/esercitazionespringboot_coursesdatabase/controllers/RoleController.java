package it.course.esercitazionespringboot_coursesdatabase.controllers;

import it.course.esercitazionespringboot_coursesdatabase.business.RoleService;
import it.course.esercitazionespringboot_coursesdatabase.models.Role;
import it.course.esercitazionespringboot_coursesdatabase.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/apiRole")
public class RoleController {

    private final RoleRepository roleRepository;

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleRepository roleRepository,
                          RoleService roleService) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<ArrayList<Role>> getRoles(){
        ArrayList<Role> roles = (ArrayList<Role>) roleRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<Role> createRole(@RequestBody Role newRole){
        Role role = roleRepository.save(newRole);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/role/{id}")
    public  ResponseEntity<HttpStatus> deleteRole(@PathVariable Integer id){
        roleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Role> updateRoles(@PathVariable("id") Integer id, @RequestBody Role modifiedRole) {
        Role role = roleRepository.findById(id).get();
        role.setName(modifiedRole.getName());
        return new ResponseEntity<>(roleRepository.save(role), HttpStatus.OK);
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
