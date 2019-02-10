package io.github.opensanca.archvalidator;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;

public class IgnoreTests implements ImportOption {

    @Override
    public boolean includes(final Location location) {
        return !location.contains("/test/");
    }
}
