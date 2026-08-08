package com.codymitra.shared_service.modules.stock_group.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.modules.stock_group.dtos.CreateStockRequest;
import com.codymitra.shared_service.modules.stock_group.dtos.StockGroupDTO;
import com.codymitra.shared_service.modules.stock_group.entities.StockGroupEntity;
import com.codymitra.shared_service.modules.stock_group.mappers.StockGroupMapper;
import com.codymitra.shared_service.modules.stock_group.repositories.StockGroupRepository;
import com.codymitra.shared_service.modules.stock_group.services.StockGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockGroupServiceImpl implements StockGroupService {

    private final StockGroupRepository stockGroupRepository;

//    @Override
//    public List<StockGroupDTO> getAllStockGroup(){
//
//    }


    @Override
    public StockGroupDTO create(CreateStockRequest createStockRequest){

        if(stockGroupRepository.existsByName(createStockRequest.name())){
            throw new DataAlreadyExistsException("Stock Group Already exists with this name");
        }
        StockGroupEntity group = StockGroupMapper.stockGroupEntity(createStockRequest);
        StockGroupEntity createGroup = stockGroupRepository.save(group);
        return StockGroupMapper.stockDTO(createGroup);
    }

}
