package com.example.crudresttest.api.v2;

import com.example.crudresttest.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record UserFilter(String nameContains, String usernameContains, String emailContains, String genderLike,
                         String creditCardLike) {
    public Specification<User> toSpecification() {
        return Specification.where(nameContainsSpec())
                .and(usernameContainsSpec())
                .and(emailContainsSpec())
                .and(genderLikeSpec())
                .and(creditCardLikeSpec());
    }

    private Specification<User> nameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(nameContains)
                ? cb.like(cb.lower(root.get("name")), "%" + nameContains.toLowerCase() + "%")
                : null);
    }

    private Specification<User> usernameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(usernameContains)
                ? cb.like(cb.lower(root.get("username")), "%" + usernameContains.toLowerCase() + "%")
                : null);
    }

    private Specification<User> emailContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(emailContains)
                ? cb.like(root.get("email"), "%" + emailContains + "%")
                : null);
    }

    private Specification<User> genderLikeSpec() {
        return ((root, query, cb) -> StringUtils.hasText(genderLike)
                ? cb.like(root.get("gender"), genderLike)
                : null);
    }

    private Specification<User> creditCardLikeSpec() {
        return ((root, query, cb) -> StringUtils.hasText(creditCardLike)
                ? cb.like(root.get("creditCard"), creditCardLike)
                : null);
    }
}