package com.QuickLogistics.QuickLogisticsHub.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.QuickLogistics.QuickLogisticsHub.entity.Package;
import org.springframework.stereotype.Service;

import com.QuickLogistics.QuickLogisticsHub.repository.PackageRepository;

@Service
public class PackageService {
    private final PackageRepository packageRepository;
    private final ExecutorService executorService;

    public PackageService(PackageRepository packageRepository, ExecutorService executorService) {
        this.packageRepository = packageRepository;
        this.executorService = executorService;
    }

    public List<Package> getAllPackages() {
        return new ArrayList<>(packageRepository.getAllPackages().values());
    }

    public Package addPackage(String destination, double weight, String deliveryType) {
        Package newPackage = packageRepository.addPackage(destination, weight, "PENDING", deliveryType);

        executorService.submit(() -> {
            try{
                Thread.sleep(3000);
                packageRepository.updatePackageStatus(newPackage.getId(), "SORTED");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        return newPackage;
    }

    public double getAnalytics() {
        return packageRepository.getAnalytics();
    }
}
