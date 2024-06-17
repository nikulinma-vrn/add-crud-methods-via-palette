package com.example.crudresttest.api.v3;

import com.example.crudresttest.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record UserDtoFilter(String nameContains, String usernameContains, String emailContains) {
    public Specification<User> toSpecification() {
        return Specification.where(nameContainsSpec())
                .and(usernameContainsSpec())
                .and(emailContainsSpec());
    }

    private Specification<User> nameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(nameContains)
                ? cb.like(root.get("name"), "%" + nameContains + "%")
                : null);
    }

    private Specification<User> usernameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(usernameContains)
                ? cb.like(root.get("username"), "%" + usernameContains + "%")
                : null);
    }

    private Specification<User> emailContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(emailContains)
                ? cb.like(root.get("email"), "%" + emailContains + "%")
                : null);
    }
}