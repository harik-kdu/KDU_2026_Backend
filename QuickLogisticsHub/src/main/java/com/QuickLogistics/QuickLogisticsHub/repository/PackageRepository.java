package com.QuickLogistics.QuickLogisticsHub.repository;

import java.util.HashMap;
import java.util.Map;
import com.QuickLogistics.QuickLogisticsHub.entity.Package;
import org.springframework.stereotype.Repository;

@Repository
public class PackageRepository {
    private static Long idCounter = 0L;
    private static final Map<Long, Package> packages = new HashMap<>();

    public PackageRepository() {

        packages.put(++idCounter, new Package(idCounter.toString(), "Bengaluru", 5.0, "PENDING", "EXPRESS"));
        packages.put(++idCounter, new Package(idCounter.toString(), "Delhi", 10.0, "SORTED", "STANDARD"));
        packages.put(++idCounter, new Package(idCounter.toString(), "Mumbai", 2.5, "PENDING", "EXPRESS"));
    }   

    public Map<Long, Package> getAllPackages() {
        return packages;
    }

    public Package addPackage(String destination, double weight, String status, String deliveryType) {
        if(packages.values().stream().anyMatch(p -> p.getDestination().equals(destination) && p.getWeight() == weight)) {
            throw new IllegalArgumentException("Package with the same destination and weight already exists.");
        }
        Package newPackage = new Package((++idCounter).toString(), destination, weight, status, deliveryType);
        packages.put(idCounter, newPackage);
        return newPackage;
    }

    public double getAnalytics() {
        return packages
            .values()
            .stream()
            .filter(p -> p.getStatus().equals("SORTED"))
            .mapToDouble(p -> p.getWeight()*2.5)
            .sum();
    }

    public void updatePackageStatus(String id, String status) {
        Package pkg = packages.get(Long.parseLong(id));
        if (pkg != null) {
            packages.put(Long.parseLong(id), new Package(id, pkg.getDestination(), pkg.getWeight(), status, pkg.getDeliveryType()));
        }
    }
    
}
