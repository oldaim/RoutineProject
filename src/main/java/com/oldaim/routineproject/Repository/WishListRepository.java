package com.oldaim.routineproject.Repository;

import com.oldaim.routineproject.entity.work.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    @Query("select wish from WishList wish WHERE wish.content = ?1")
    WishList findByContent(String content);

    @Query("select wish from WishList wish where wish.member.id = ?1")
    List<WishList> findAllByMember(Long id);

}
