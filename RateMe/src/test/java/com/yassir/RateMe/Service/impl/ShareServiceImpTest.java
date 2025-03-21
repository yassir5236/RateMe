package com.yassir.RateMe.Service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Dto.Share.ShareRequestDTO;
import com.yassir.RateMe.Dto.Share.ShareResponseDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Mapper.IShareMapper;
import com.yassir.RateMe.Model.Entity.Place;
import com.yassir.RateMe.Model.Entity.Share;
import com.yassir.RateMe.Model.Entity.User;
import com.yassir.RateMe.Repository.PlaceRepository;
import com.yassir.RateMe.Repository.ShareRepository;
import com.yassir.RateMe.Repository.UserRepository;
import com.yassir.RateMe.Service.impl.ShareServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShareServiceImpTest {

    @Mock
    private ShareRepository shareRepository;

    @Mock
    private IShareMapper shareMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private ShareServiceImp shareService;

    private Share share;
    private User user;
    private Place place;
    private ShareRequestDTO shareRequestDTO;
    private ShareResponseDTO shareResponseDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        place = new Place();
        place.setId(1L);
        share = new Share();
        share.setId(1L);
        share.setUser(user);
        share.setPlace(place);

        shareRequestDTO = new ShareRequestDTO("My Share Title", 1L, 1L);
        shareRequestDTO = new ShareRequestDTO("My Share Title2", 1L, 1L);
    }

    @Test
    void testCreateShare_Success() {
        User mockUser = new User();
        mockUser.setId(1L);

        Place mockPlace = new Place();
        mockPlace.setId(1L);

        Share mockShare = new Share();
        mockShare.setId(1L);
        mockShare.setTitle("Test Title");
        mockShare.setUser(mockUser);
        mockShare.setPlace(mockPlace);

        EmbeddedUserDTO userDTO = new EmbeddedUserDTO(1L, "Test User", "profile-pic.jpg");
        PlaceResponseDTO placeDTO = new PlaceResponseDTO(1L, "Test Place",null,null,null,null,null,null,null);
        ShareResponseDTO expectedResponse = new ShareResponseDTO(1L, "Test Title", userDTO, placeDTO);

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(placeRepository.findById(1L)).thenReturn(Optional.of(mockPlace));
        when(shareMapper.toEntity(any(ShareRequestDTO.class))).thenReturn(mockShare);
        when(shareRepository.save(any(Share.class))).thenReturn(mockShare);
        when(shareMapper.toResponseDto(any(Share.class))).thenReturn(expectedResponse);

        ShareResponseDTO result = shareService.createShare(shareRequestDTO);

        assertNotNull(result, "Le ShareResponseDTO ne doit pas être null");
        assertEquals(1L, result.id(), "L'ID ne correspond pas");
        assertEquals("Test Title", result.title(), "Le titre ne correspond pas");

        verify(shareRepository).save(any(Share.class));
        verify(shareMapper).toResponseDto(any(Share.class));
    }


    @Test
    void testCreateShare_UserNotFound_ShouldThrowException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> shareService.createShare(shareRequestDTO));
        assertEquals("user not found", exception.getMessage());
    }

    @Test
    void testCreateShare_PlaceNotFound_ShouldThrowException() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(placeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> shareService.createShare(shareRequestDTO));
        assertEquals("place not found", exception.getMessage());
    }



    @Test
    void testGetShareById_Success() {
        Long shareId = 1L;

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("Test User");
        mockUser.setProfilePicture("profile-pic.jpg");

        Place mockPlace = new Place();
        mockPlace.setId(1L);
        mockPlace.setName("Test Place");

        Share mockShare = new Share();
        mockShare.setId(shareId);
        mockShare.setTitle("Test Share");
        mockShare.setUser(mockUser);
        mockShare.setPlace(mockPlace);

        EmbeddedUserDTO userDTO = new EmbeddedUserDTO(1L, "Test User", "profile-pic.jpg");
        PlaceResponseDTO placeDTO = new PlaceResponseDTO(1L, "Test Place",null,null,null,null,null,null,null);
        ShareResponseDTO mockShareResponseDTO = new ShareResponseDTO(1L, "Test Share", userDTO, placeDTO);

        when(shareRepository.findById(shareId)).thenReturn(Optional.of(mockShare));
        when(shareMapper.toResponseDto(mockShare)).thenReturn(mockShareResponseDTO);

        ShareResponseDTO result = shareService.getShareById(shareId);

        assertNotNull(result, "Le ShareResponseDTO ne doit pas être null");
        assertEquals(1L, result.id(), "L'ID du ShareResponseDTO ne correspond pas");
        assertEquals("Test Share", result.title(), "Le titre ne correspond pas");
        assertEquals("Test User", result.user().username(), "Le nom d'utilisateur ne correspond pas");
        assertEquals("profile-pic.jpg", result.user().profilePicture(), "L'image de profil ne correspond pas");

        verify(shareRepository).findById(shareId);
        verify(shareMapper).toResponseDto(mockShare);
    }



    @Test
    void testGetShareById_NotFound_ShouldThrowException() {
        when(shareRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> shareService.getShareById(1L));
        assertEquals("Share not found with ID: 1", exception.getMessage());
    }

    @Test
    void testUpdateShare_Success() {
        // Création d'un Share valide
        User mockUser = new User();
        mockUser.setId(1L);

        Place mockPlace = new Place();
        mockPlace.setId(1L);

        Share mockShare = new Share();
        mockShare.setId(1L);
        mockShare.setTitle("Updated Title");
        mockShare.setUser(mockUser);
        mockShare.setPlace(mockPlace);

        EmbeddedUserDTO userDTO = new EmbeddedUserDTO(1L, "Test User", "profile-pic.jpg");
        PlaceResponseDTO placeDTO = new PlaceResponseDTO(1L, "Test Place",null,null,null,null,null,null,null);
        ShareResponseDTO expectedResponse = new ShareResponseDTO(1L, "Updated Title", userDTO, placeDTO);

        when(shareRepository.findById(1L)).thenReturn(Optional.of(mockShare));
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(placeRepository.findById(1L)).thenReturn(Optional.of(mockPlace));
        when(shareRepository.save(any(Share.class))).thenReturn(mockShare);
        when(shareMapper.toResponseDto(any(Share.class))).thenReturn(expectedResponse);

        ShareResponseDTO result = shareService.updateShare(1L, shareRequestDTO);

        assertNotNull(result, "Le ShareResponseDTO ne doit pas être null");
        assertEquals(1L, result.id(), "L'ID ne correspond pas");
        assertEquals("Updated Title", result.title(), "Le titre ne correspond pas");

        verify(shareRepository).findById(1L);
        verify(shareRepository).save(any(Share.class));
        verify(shareMapper).toResponseDto(any(Share.class));
    }


    @Test
    void testUpdateShare_NotFound_ShouldThrowException() {
        when(shareRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> shareService.updateShare(1L, shareRequestDTO));
        assertEquals("Share not found with ID: 1", exception.getMessage());
    }

    @Test
    void testDeleteShare_Success() {
        when(shareRepository.existsById(1L)).thenReturn(true);
        doNothing().when(shareRepository).deleteById(1L);

        assertDoesNotThrow(() -> shareService.deleteShare(1L));
        verify(shareRepository).deleteById(1L);
    }

    @Test
    void testDeleteShare_NotFound_ShouldThrowException() {
        when(shareRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> shareService.deleteShare(1L));
        assertEquals("Share not found with ID: 1", exception.getMessage());
    }

    @Test
    void testGetSharesByUserId_Success() {
        when(shareRepository.findByUserId(1L)).thenReturn(Arrays.asList(share));
        when(shareMapper.toResponseDto(share)).thenReturn(shareResponseDTO);

        List<ShareResponseDTO> result = shareService.getSharesByUserId(1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testGetSharesByUserId_EmptyList() {
        when(shareRepository.findByUserId(1L)).thenReturn(Arrays.asList());

        List<ShareResponseDTO> result = shareService.getSharesByUserId(1L);

        assertTrue(result.isEmpty());
    }
}
