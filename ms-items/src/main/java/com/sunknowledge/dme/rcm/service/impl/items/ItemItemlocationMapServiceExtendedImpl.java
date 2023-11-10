package com.sunknowledge.dme.rcm.service.impl.items;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ItemItemlocationMap;
import com.sunknowledge.dme.rcm.repository.items.ItemItemlocationMapRepositoryExtended;
import com.sunknowledge.dme.rcm.securityutil.InternalAccessTokenUtilities;
import com.sunknowledge.dme.rcm.service.dto.ItemItemlocationMapDTO;
import com.sunknowledge.dme.rcm.service.dto.ItemMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.items.ItemItemlocationMapInputDTO;
import com.sunknowledge.dme.rcm.service.items.ItemItemlocationMapServiceExtended;
import com.sunknowledge.dme.rcm.service.items.ItemMasterServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.ItemItemlocationMapMapper;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import com.dropbox.core.InvalidAccessTokenException;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Primary
@Service
public class ItemItemlocationMapServiceExtendedImpl implements ItemItemlocationMapServiceExtended {
    private final Logger log = LoggerFactory.getLogger(ItemItemlocationMapServiceExtendedImpl.class);
    @Autowired
    ItemItemlocationMapRepositoryExtended itemItemlocationMapRepositoryExtended;
    @Autowired
    ItemItemlocationMapMapper itemItemlocationMapMapper;

    @Autowired
    ItemMasterServiceExtended itemMasterServiceExtended;

    @Override
    public ItemItemlocationMapDTO save(ItemItemlocationMapDTO itemItemlocationMapDTO) {
        return null;
    }

    @Override
    public ItemItemlocationMapDTO update(ItemItemlocationMapDTO itemItemlocationMapDTO) {
        return null;
    }

    @Override
    public Optional<ItemItemlocationMapDTO> partialUpdate(ItemItemlocationMapDTO itemItemlocationMapDTO) {
        return Optional.empty();
    }

    @Override
    public Page<ItemItemlocationMapDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<ItemItemlocationMapDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO saveItemItemlocationMap(ItemItemlocationMapInputDTO itemItemlocationMapInputDTO) {
         try {
            if (itemItemlocationMapInputDTO.getItemLocationId() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (ItemLocation_Id)");
            }else if (itemItemlocationMapInputDTO.getItemIdList() == null) {
                throw new InvalidAttributeValueException("Invalid Attribute (Item_Id)");
            } else if (itemItemlocationMapInputDTO.getItemIdList().size() == 0) {
                throw new InputMismatchException("Item_Ids must be provided");
            }
             Set<Long> itemSet = itemItemlocationMapInputDTO.getItemIdList().stream().filter(x -> x != 0 && x != null).collect(Collectors.toSet());
             List<Long> itemList = itemSet.stream().collect(Collectors.toList());
             Object itemLocationDetails = getItemlocation(itemItemlocationMapInputDTO.getItemLocationId());
             List<ItemMasterDTO> itemMasterDTOS = itemMasterServiceExtended.findByItemIdIn(itemList);
             if(itemMasterDTOS.size() > 0 && itemLocationDetails != null) {
                 String itemLocationName = String.valueOf(((JSONObject)((JSONArray) itemLocationDetails).get(0)).get("itemLocationName"));
                 List<String> duplicateIds = itemItemlocationMapRepositoryExtended.findByItemLocationIdAndItemIdInAndStatusIgnoreCase(
                     itemItemlocationMapInputDTO.getItemLocationId(), itemList, "active")
                     .stream().map(x -> x.getItemId() + "_" + x.getItemLocationId()).collect(Collectors.toList());

//                 System.out.println("updateDTOs => " +updateDTOs);
//                 if (updateDTOs.size() > 0) {
//                     updateDTOs.stream().peek(x -> {
//                         x.setStatus("inactive");
//                         x.setUpdatedDate(LocalDate.now());
//                         x.setUpdatedById(1L);
//                         x.setUpdatedByName("Abhijit");
//                     }).collect(Collectors.toList());
//                     itemItemlocationMapRepositoryExtended.saveAll(updateDTOs);
//                 }

                 List<ItemItemlocationMapDTO> itemItemlocationMapDTOS = new ArrayList<ItemItemlocationMapDTO>();
                 Boolean isDataSaved = false;
                 for (ItemMasterDTO data : itemMasterDTOS) {
                     if(!duplicateIds.contains(data.getItemId() + "_" + itemItemlocationMapInputDTO.getItemLocationId())){
                         ItemItemlocationMapDTO itemItemlocationMapDTO = new ItemItemlocationMapDTO();

                         itemItemlocationMapDTO.setItemId(data.getItemId());
                         itemItemlocationMapDTO.setItemName(data.getItemName());
                         itemItemlocationMapDTO.setItemNo(data.getItemNumber());
                         itemItemlocationMapDTO.setItemUom(data.getItemUom());
                         itemItemlocationMapDTO.setItemLocationId(itemItemlocationMapInputDTO.getItemLocationId());
                         itemItemlocationMapDTO.setItemLocationName(itemLocationName);
                         itemItemlocationMapDTO.setStatus("active");
                         itemItemlocationMapDTO.setCreatedById(3L);
                         itemItemlocationMapDTO.setCreatedByName("Ritam Test");
                         itemItemlocationMapDTO.setCreatedDate(LocalDate.now());
                         itemItemlocationMapDTO.setUpdatedById(null);
                         itemItemlocationMapDTO.setUpdatedByName(null);
                         itemItemlocationMapDTO.setItemItemlocationMapUuid(UUID.randomUUID());

                         itemItemlocationMapDTOS.add(itemItemlocationMapDTO);
                         isDataSaved = true;
                     }
                     else{
                         System.out.println("Duplicate " +data.getItemId() + "_" + itemItemlocationMapInputDTO.getItemLocationId());
                     }
                 }
                 List<ItemItemlocationMapDTO> savedItemItemlocationMapDTOList = itemItemlocationMapMapper.toDto(
                     itemItemlocationMapRepositoryExtended.saveAll(itemItemlocationMapMapper.toEntity(itemItemlocationMapDTOS))
                 );

                 return new ResponseDTO(isDataSaved, isDataSaved?"Successfully Saved.":"Data already exist.", List.of(savedItemItemlocationMapDTOList));
             }
             else{
                 return new ResponseDTO(false, "Data Not Found.", new ArrayList<>());
             }
        }catch (InvalidAttributeValueException e) {
             log.error("=====>> Error : "+e);
             throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO deactiveItemItemLocationMap(ItemItemlocationMapInputDTO itemItemlocationMapInputDTO) {
        List<ItemItemlocationMap> updateDTOs = itemItemlocationMapRepositoryExtended.findByItemLocationIdAndItemIdInAndStatusIgnoreCase(
            itemItemlocationMapInputDTO.getItemLocationId(), itemItemlocationMapInputDTO.getItemIdList(),"active");
        if(updateDTOs.size() > 0) {
            updateDTOs.stream().peek(x -> x.setStatus("inactive")).collect(Collectors.toList());
            List<ItemItemlocationMapDTO> updatedItemItemlocationMapDTOList = itemItemlocationMapMapper.toDto(
                itemItemlocationMapRepositoryExtended.saveAll(updateDTOs));
            return new ResponseDTO(true, "Successfully Saved.", List.of(updatedItemItemlocationMapDTOList));
        }else{
            return new ResponseDTO(false, "Data Not Found.", new ArrayList());
        }
    }

    @Override
    public List<ItemItemlocationMapDTO> getItemItemlocationMapByItemId(Long itemId) {
        return itemItemlocationMapMapper.toDto(itemItemlocationMapRepositoryExtended.findByItemIdAndStatusIgnoreCase(itemId,"active"));
    }

    @Override
    public List<ItemItemlocationMapDTO> getItemItemlocationMapByItemLocationId(Long itemLocationId) {
        return itemItemlocationMapMapper.toDto(itemItemlocationMapRepositoryExtended.findByItemLocationIdAndStatusIgnoreCase(itemLocationId,"active"));
    }

    @Override
    public List<ItemItemlocationMapDTO> getItemItemlocationMapByItemLocationName(String itemLocationName) {
        return itemItemlocationMapMapper.toDto(itemItemlocationMapRepositoryExtended.findByItemLocationNameLikeIgnoreCaseAndStatusIgnoreCase("%"+itemLocationName+"%","active"));
    }

    @Override
    public List<ItemItemlocationMapDTO> getItemItemlocationMapByItemName(String itemName) {
        return itemItemlocationMapMapper.toDto(itemItemlocationMapRepositoryExtended.findByItemNameLikeIgnoreCaseAndStatusIgnoreCase("%"+itemName+"%","active"));
    }

    @Override
    public List<ItemItemlocationMapDTO> getItemItemlocationMapByStatus(String status) {
        return itemItemlocationMapMapper.toDto(itemItemlocationMapRepositoryExtended.findByStatusIgnoreCase(status));
    }

    private Object getItemlocation(Long itemLocationId) {
        HashMap<String,String> d = new HashMap<String, String>();
        d.put("itemLocationId",itemLocationId.toString());
        d.put("itemLocationName","Test");

        JSONArray jArr = new JSONArray();
        JSONObject jObj = new JSONObject();
        String responseBody = new String("[]");
        try {
            //==================== Get the access token ====================
            String accessToken = InternalAccessTokenUtilities.getAccessToken();
            JSONParser parser = new JSONParser();
            JSONObject accessTokenJson = (JSONObject) parser.parse(accessToken);
            String token = accessTokenJson.get("access_token").toString();

            if (!token.equalsIgnoreCase("NOT_AVAILABLE")) {
                //===================== Call the MS API with Token =============
                CommonUtilities commonUtilitiesObj = new CommonUtilities();
                Properties propData = commonUtilitiesObj.readPropertiesFile("/project-properties-files/url_config.properties");

                String url = propData.getProperty("settings_url");
                String param="?itemLocationId={itemLocationId}";
                String completeUrl = url + param;
                RestTemplate restTemplateData = new RestTemplate();
                HttpHeaders headersData = new HttpHeaders();
                headersData.add("Authorization", "Bearer " + token);
                HttpEntity entityData = new HttpEntity<>(headersData);
                ResponseEntity responseData = restTemplateData.exchange(
                    completeUrl,
                    HttpMethod.GET,
                    entityData,
                    String.class,
                    itemLocationId);
                if (responseData.getStatusCode() == HttpStatus.OK) {
                    responseBody = (String) responseData.getBody();
                } else {
                    jObj = new JSONObject();
                    jObj.put("status", false);
                    jObj.put("message", "API Error: No Response Data Available");
                    jObj.put("data", new JSONArray());
                    jArr.add(jObj);
                    responseBody = jArr.toString();
                }
            } else {
                jObj = new JSONObject();
                jObj.put("status", false);
                jObj.put("message", "Missing Access Token");
                jObj.put("data", new JSONArray());
                jArr.add(jObj);
                responseBody = jArr.toString();
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                try {
                    throw new InvalidAccessTokenException("REQUEST_CODE_498", "Invalid Access Token - " + e.getMessage());
                } catch (InvalidAccessTokenException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request: Request Datatype Mismatch/Error in Request Body");
            } else {
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }
        JSONParser parser = new JSONParser();
        try {
            if((Boolean)((JSONObject) parser.parse(responseBody)).get("status")){
                return ((JSONObject) parser.parse(responseBody)).get("data");
            }else{
                return null;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemItemlocationMapDTO> getAll()
    {
        return itemItemlocationMapMapper.toDto(itemItemlocationMapRepositoryExtended.findAll());
    }

    @Override
    public ResponseDTO setItemItemlocationMapStatusById(Long itemItemlocationMapId, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<ItemItemlocationMap> itemItemlocationMap = itemItemlocationMapRepositoryExtended.findById(itemItemlocationMapId);
                itemItemlocationMap.get().setStatus(status);
                itemItemlocationMapRepositoryExtended.save(itemItemlocationMap.get());
                return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", List.of(itemItemlocationMap.get())));
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",new ArrayList<>()));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", new ArrayList<>()));
        }
    }
}
