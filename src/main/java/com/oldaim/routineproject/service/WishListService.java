package com.oldaim.routineproject.service;

import com.oldaim.routineproject.Repository.WishListRepository;
import com.oldaim.routineproject.dto.WishListDto;
import com.oldaim.routineproject.entity.Member;
import com.oldaim.routineproject.entity.work.CheckList;
import com.oldaim.routineproject.entity.work.WishList;
import com.oldaim.routineproject.entity.work.WorkCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class WishListService {

    private final WishListRepository wishListRepository;

    public Long wishSave(Member member , WishListDto dto){

        WishList wishListEntity = wishDtoToEntity(member,dto);

        wishListRepository.save(wishListEntity);

        return wishListEntity.getId();

    }

    public Long wishModify(WishListDto dtoOld, WishListDto dtoNew){
        WishList wishList = wishListRepository.findByContent(dtoOld.getContent());

        wishList.changeContent(dtoNew.getContent());

        wishListRepository.save(wishList);

        return wishList.getId();
    }

    public List<WishListDto> wishFindAll(Long member_id){

        List<WishList> wishEntityList = wishListRepository.findAllByMember(member_id);

        List<WishListDto> dtoList = new ArrayList<>();

        for (WishList wishList : wishEntityList) {
            if(wishList.getCheckList() == CheckList.UNDO) {
                dtoList.add(wishEntityToDto(wishList));
            }
        }

        return dtoList;

    }

    public void wishDelete(WishListDto dto){
        Long wishListId = wishListRepository.findByContent(dto.getContent()).getId();

        wishListRepository.deleteById(wishListId);

    }

    public void wishListUndoToDo(WishListDto dto){
        WishList wishListEntity = wishListRepository.findByContent(dto.getContent());

        wishListEntity.changeCheckListUndoToDo();

        wishListRepository.save(wishListEntity);
    }

    private WishListDto wishEntityToDto(WishList wishList) {
        return WishListDto
                .builder()
                .checkList(wishList.getCheckList().name())
                .content(wishList.getContent())
                .workCategory(wishList.getWorkCategory().name())
                .build();
    }

    private WishList wishDtoToEntity(Member member, WishListDto dto){
        return WishList
                .builder()
                .content(dto.getContent())
                .workCategory(WorkCategory.WishList)
                .checkList(CheckList.UNDO)
                .member(member)
                .build();
    }

}
