package com.yassir.RateMe.Service;

import com.yassir.RateMe.Dto.Share.ShareRequestDTO;
import com.yassir.RateMe.Dto.Share.ShareResponseDTO;

import java.util.List;

public interface IShareService {
    ShareResponseDTO createShare(ShareRequestDTO shareRequestDTO);

    ShareResponseDTO getShareById(Long shareId);

    ShareResponseDTO updateShare(Long id, ShareRequestDTO shareRequestDTO);

    List<ShareResponseDTO> getAllShares();

    void deleteShare(Long shareId);

    List<ShareResponseDTO> getSharesByUserId(Long userId);

}
