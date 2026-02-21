package com.rick1135.eventostech.repositories;

import com.rick1135.eventostech.entity.Address;
import com.rick1135.eventostech.entity.Event;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class EventSpecification {
    public static Specification<Event> hasTitle(String title) {
        if(title == null || title.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Event> hasCity(String city) {
        if(city == null || city.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            Join<Event, Address> addressJoin = root.join("address");
            return criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    public static Specification<Event> hasUf(String state) {
        if(state == null || state.isEmpty()) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            Join<Event, Address> addressJoin = root.join("address");
            return criteriaBuilder.like(criteriaBuilder.lower(addressJoin.get("uf")), "%" + state.toLowerCase() + "%");
        };
    }

    public static Specification<Event> isRemote(Boolean remote) {
        if(remote == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("remote"), remote);
    }

    public static Specification<Event> startDateAfter(LocalDateTime startDate) {
        if(startDate == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), startDate);
    }

    public static Specification<Event> endDateBefore(LocalDateTime endDate) {
        if(endDate == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), endDate);
    }
}
