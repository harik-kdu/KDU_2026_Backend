package com.QuickLogistics.QuickLogisticsHub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.QuickLogistics.QuickLogisticsHub.entity.Package;
import com.QuickLogistics.QuickLogisticsHub.dto.PackageRequest;
import com.QuickLogistics.QuickLogisticsHub.services.PackageService;

@RestController
@RequestMapping("/packages")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping
    @PreAuthorize("hasRole('MANAGER')")
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addPackage(@RequestBody PackageRequest packageRequest) {
        try {
            Package newPackage = packageService.addPackage(
                packageRequest.getDestination(),
                packageRequest.getWeight(),
                packageRequest.getDeliveryType()
            );
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(newPackage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
        }
    }

    @GetMapping("/analytics/revenue")
    @PreAuthorize("hasAnyRole('MANAGER', 'DRIVER')")
    public ResponseEntity<Double> getAnalytics() {
        return ResponseEntity.ok(packageService.getAnalytics());
    }
}
