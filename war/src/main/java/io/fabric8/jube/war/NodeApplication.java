/**
 *  Copyright 2005-2014 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.jube.war;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import io.fabric8.cxf.endpoint.EnableJMXFeature;
import io.fabric8.jube.apimaster.ApiMasterService;

import io.fabric8.jube.apimaster.ApiV1Beta1;
import org.apache.cxf.feature.LoggingFeature;

@ApplicationPath("/api")
public class NodeApplication extends Application {

    @Produces
    private JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();

    @Inject
    private ApiMasterService apiMasterService;

    @Inject
    private ApiV1Beta1 v1beta1;

    public NodeApplication() {
        System.out.println("==================== started NodeApplication");
    }

    @Override
    public Set<Object> getSingletons() {
        return new HashSet<Object>(
                Arrays.asList(
                        apiMasterService,
                        v1beta1,
                        jacksonJsonProvider,
                        // TODO
                        // new SwaggerFeature(),
                        new EnableJMXFeature(),
                        new LoggingFeature()
                )
        );
    }
}

