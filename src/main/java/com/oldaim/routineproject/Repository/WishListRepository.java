package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.work.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Long> {
}
