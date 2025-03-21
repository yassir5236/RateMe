package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Share.ShareRequestDTO;
import com.yassir.RateMe.Dto.Share.ShareResponseDTO;
import com.yassir.RateMe.Mapper.IShareMapper;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.Share;
import com.yassir.RateMe.Model.Entity.User;
import com.yassir.RateMe.Repository.PlaceRepository;
import com.yassir.RateMe.Repository.ShareRepository;
import com.yassir.RateMe.Repository.UserRepository;
import com.yassir.RateMe.Service.IShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;




@Service
public class ShareServiceImp implements IShareService {


    private final ShareRepository shareRepository;
    private final IShareMapper shareMapper;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;


    @Autowired
    public ShareServiceImp(ShareRepository shareRepository, IShareMapper shareMapper , UserRepository userRepository, PlaceRepository placeRepository) {
        this.shareRepository = shareRepository;
        this.shareMapper = shareMapper;
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }


    @Override
    public ShareResponseDTO createShare(ShareRequestDTO shareRequestDTO) {
        User user = userRepository.findById(shareRequestDTO.userId())
                .orElseThrow(()->new RuntimeException("user not found"));
        Place place =placeRepository.findById(shareRequestDTO.placeId())
                .orElseThrow(()->new RuntimeException("place not found"));

        Share share = shareMapper.toEntity(shareRequestDTO);
        share.setUser(user);
        share.setPlace(place);
        Share savedShare = shareRepository.save(share);
        return shareMapper.toResponseDto(savedShare);
    }


    @Override
    public ShareResponseDTO getShareById(Long shareId) {
        Share share = shareRepository.findById(shareId)
                .orElseThrow(() -> new IllegalArgumentException("Share not found with ID: " + shareId));
        return shareMapper.toResponseDto(share);
    }

    @Override
    public ShareResponseDTO updateShare(Long id, ShareRequestDTO shareRequestDTO) {
        Share existingShare = shareRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Share not found with ID: " + id));
        User user = userRepository.findById(shareRequestDTO.userId())
                .orElseThrow(()->new RuntimeException("user not found"));
        Place place =placeRepository.findById(shareRequestDTO.placeId())
                .orElseThrow(()->new RuntimeException("place not found"));

        existingShare.setUser(user);
        existingShare.setPlace(place);
        Share updatedShare = shareRepository.save(existingShare);
        return shareMapper.toResponseDto(updatedShare);
    }

    @Override
    public List<ShareResponseDTO> getAllShares() {
        List<Share> shares = (List<Share>) shareRepository.findAll();
        return shares.stream()
                .map(shareMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteShare(Long shareId) {
        if (!shareRepository.existsById(shareId)) {
            throw new IllegalArgumentException("Share not found with ID: " + shareId);
        }
        shareRepository.deleteById(shareId);
    }


    public List<ShareResponseDTO> getSharesByUserId(Long userId) {
        List<Share> shares = shareRepository.findByUserId(userId);
        return shares.stream()
                .map(shareMapper::toResponseDto)
                .collect(Collectors.toList());
    }



























}
