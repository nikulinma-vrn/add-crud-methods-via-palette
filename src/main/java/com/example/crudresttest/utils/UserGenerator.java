package com.example.crudresttest.utils;

import com.example.crudresttest.entity.User;
import com.example.crudresttest.repository.UserRepository;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.types.enums.NameType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserGenerator {

    @Autowired
    private static UserRepository userRepository;

    public UserGenerator(UserRepository userRepository) {
        UserGenerator.userRepository = userRepository;
    }

    public static void bulkUserAdd(int count) {
        MockNeat mockNeat = MockNeat.threadLocal();

        for (int i = 0; i < count; i++) {
            User user = new User(
                    UUID.randomUUID(),
                    mockNeat.names().type(NameType.FIRST_NAME).get(),
                    mockNeat.users().get(),
                    mockNeat.emails().get(),
                    mockNeat.genders().get(),
                    mockNeat.creditCards().get()
            );
            userRepository.save(user);
        }
    }
}