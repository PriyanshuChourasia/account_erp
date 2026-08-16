package com.codymitra.shared_service.modules.voucher_reference.services.impl;

import com.codymitra.shared_service.exceptionHandler.exceptions.DataAlreadyExistsException;
import com.codymitra.shared_service.exceptionHandler.exceptions.DataNotFoundException;
import com.codymitra.shared_service.modules.voucher.entities.VoucherEntity;
import com.codymitra.shared_service.modules.voucher.repositories.VoucherRepository;
import com.codymitra.shared_service.modules.voucher_reference.dtos.CreateVoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.dtos.VoucherReferenceDTO;
import com.codymitra.shared_service.modules.voucher_reference.entities.VoucherReferenceEntity;
import com.codymitra.shared_service.modules.voucher_reference.mappers.VoucherReferenceMapper;
import com.codymitra.shared_service.modules.voucher_reference.repositories.VoucherReferenceRepository;
import com.codymitra.shared_service.modules.voucher_reference.services.VoucherReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherReferenceServiceImpl implements VoucherReferenceService {

    private final VoucherReferenceRepository voucherReferenceRepository;
    private final VoucherRepository voucherRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VoucherReferenceDTO> getAll() {
        return voucherReferenceRepository.findAll().stream()
                .map(VoucherReferenceMapper::voucherReferenceDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VoucherReferenceDTO getById(Long id) {
        return VoucherReferenceMapper.voucherReferenceDTO(findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherReferenceDTO> getByVoucherId(Long voucherId) {
        return voucherReferenceRepository.findByVoucherId(voucherId).stream()
                .map(VoucherReferenceMapper::voucherReferenceDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoucherReferenceDTO> getByRefVoucherId(Long refVoucherId) {
        return voucherReferenceRepository.findByRefVoucherId(refVoucherId).stream()
                .map(VoucherReferenceMapper::voucherReferenceDTO)
                .toList();
    }

    @Override
    @Transactional
    public VoucherReferenceDTO create(CreateVoucherReferenceDTO request) {
        if (request.voucherId().equals(request.refVoucherId())) {
            throw new IllegalArgumentException("Voucher cannot reference itself");
        }
        VoucherEntity voucher = findVoucher(request.voucherId());
        VoucherEntity refVoucher = findVoucher(request.refVoucherId());
        if (voucherReferenceRepository.existsByVoucherIdAndRefVoucherId(request.voucherId(), request.refVoucherId())) {
            throw new DataAlreadyExistsException("Voucher reference already exists");
        }
        VoucherReferenceEntity saved = voucherReferenceRepository.save(
                VoucherReferenceMapper.voucherReferenceEntity(request, voucher, refVoucher)
        );
        return VoucherReferenceMapper.voucherReferenceDTO(saved);
    }

    @Override
    @Transactional
    public VoucherReferenceDTO update(Long id, CreateVoucherReferenceDTO request) {
        VoucherReferenceEntity voucherReference = findById(id);
        if (request.voucherId().equals(request.refVoucherId())) {
            throw new IllegalArgumentException("Voucher cannot reference itself");
        }
        VoucherEntity voucher = findVoucher(request.voucherId());
        VoucherEntity refVoucher = findVoucher(request.refVoucherId());
        boolean sameReference = voucherReference.getVoucher().getId().equals(request.voucherId())
                && voucherReference.getRefVoucher().getId().equals(request.refVoucherId());
        if (!sameReference
                && voucherReferenceRepository.existsByVoucherIdAndRefVoucherId(request.voucherId(), request.refVoucherId())) {
            throw new DataAlreadyExistsException("Voucher reference already exists");
        }
        voucherReference.setVoucher(voucher);
        voucherReference.setRefVoucher(refVoucher);
        return VoucherReferenceMapper.voucherReferenceDTO(voucherReferenceRepository.save(voucherReference));
    }

    @Override
    @Transactional
    public String delete(Long id) {
        voucherReferenceRepository.delete(findById(id));
        return "Voucher reference deleted successfully";
    }

    private VoucherReferenceEntity findById(Long id) {
        return voucherReferenceRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Voucher reference does not exist")
        );
    }

    private VoucherEntity findVoucher(Long id) {
        return voucherRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("No such voucher exists")
        );
    }
}
