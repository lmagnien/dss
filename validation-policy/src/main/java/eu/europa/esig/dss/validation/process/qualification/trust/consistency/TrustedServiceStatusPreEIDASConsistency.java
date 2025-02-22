/**
 * DSS - Digital Signature Services
 * Copyright (C) 2015 European Commission, provided under the CEF programme
 * 
 * This file is part of the "DSS - Digital Signature Services" project.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package eu.europa.esig.dss.validation.process.qualification.trust.consistency;

import eu.europa.esig.dss.diagnostic.TrustedServiceWrapper;
import eu.europa.esig.dss.validation.process.qualification.EIDASUtils;
import eu.europa.esig.dss.validation.process.qualification.trust.TrustedServiceStatus;

import java.util.Date;

/**
 * Verifies status of a trusted service created before eIDAS
 *
 */
public class TrustedServiceStatusPreEIDASConsistency implements TrustedServiceCondition {

    /**
     * Default constructor
     */
    public TrustedServiceStatusPreEIDASConsistency() {
        // empty
    }

    @Override
    public boolean isConsistent(TrustedServiceWrapper trustedService) {
        Date startDate = trustedService.getStartDate();
        if (EIDASUtils.isPreEIDAS(startDate)) {
            String status = trustedService.getStatus();
            return !TrustedServiceStatus.GRANTED.getUri().equals(status) &&
                    !TrustedServiceStatus.WITHDRAWN.getUri().equals(status);
        }
        return true;
    }

}
