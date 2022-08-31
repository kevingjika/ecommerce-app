package com.application.ecommerce.services;

import com.application.ecommerce.entities.Users;
import com.application.ecommerce.exceptions.NoUsersFoundException;
import com.application.ecommerce.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    UsersRepository usersRepository ;

    public UsersService (UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users createUser (Users users) throws Exception {
        users.setRole(Users.Role.USER);
        Optional<Users> findIfUserExists = usersRepository.findByUsername(users.getUsername());
        if(findIfUserExists.isPresent()){
            throw new NoUsersFoundException("User-i ekziston.");
        }
        return usersRepository.save(users);
    }

    public void editUser(long id) throws Exception {
        Optional<Users>  findIfUsersExists = usersRepository.findById(id);
        if(!findIfUsersExists.isPresent()) {
            throw new Exception("User-i nuk ekziston.");
        }
        usersRepository.save(findIfUsersExists.get());
    }

    public void deleteUser(long id) throws Exception{
        Optional<Users> findIfUsersExists = usersRepository.findById(id);
        if(!findIfUsersExists.isPresent()) {
            throw new Exception ("User-i nuk do te fshihet.");
        }
        usersRepository.deleteById(id);
    }


}
