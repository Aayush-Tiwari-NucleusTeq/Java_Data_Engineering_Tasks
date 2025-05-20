package com.gcp.spanner.repository;

import com.gcp.spanner.entities.User;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;

public interface UserRepository extends SpannerRepository<User, Integer> {

}
