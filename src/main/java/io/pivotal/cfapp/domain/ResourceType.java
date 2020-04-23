package io.pivotal.cfapp.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.util.Assert;

// @see https://v3-apidocs.cloudfoundry.org/version/3.82.0/index.html#resources
// A subset of the available resources accessed via cf v3 api
public enum ResourceType {

    APPS("apps"),
    BUILDS("builds"),
    BUILDPACKS("buildpacks"),
    DEPLOYMENTS("deployments"),
    DOMAINS("domains"),
    DROPLETS("droplets"),
    ISOLATION_SEGMENTS("isolation_segments"),
    ORGS("organizations"),
    PACKAGES("packages"),
    PROCESSES("processes"),
    SPACES("spaces"),
    STACKS("stacks"),
    TASKS("tasks");

    private String id;

    ResourceType(String id) {
        this.id = id;
    }

    @JsonValue
    public String getId() {
        return id;
    }

    public static ResourceType from(String id) {
        ResourceType result = null;
        List<ResourceType> candidates = Arrays.asList(ResourceType.values()).stream().filter(et -> et.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
        if (candidates != null && candidates.size() == 1) {
            result = candidates.get(0);
        }
        Assert.isTrue(result != null, "Not a valid resource type identifier");
        return result;
    }
}