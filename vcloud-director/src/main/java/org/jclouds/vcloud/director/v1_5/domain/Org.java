/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.vcloud.director.v1_5.domain;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.jclouds.vcloud.director.v1_5.VCloudDirectorMediaType.NS;

import java.net.URI;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jclouds.vcloud.director.v1_5.domain.Entity.Builder;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * iRepresents an organization.
 *
 * Unit of multi-tenancy and a top-level container. Contain vDCs, TasksList, Catalogs and Shared Network entities.
 *
 * <pre>
 * &lt;xs:complexType name="OrgType"&gt;
 * </pre>
 *
 * @author Adrian Cole
 */
@XmlRootElement(namespace = NS, name = "Org")
public class Org extends Entity<Org> {

   @SuppressWarnings("unchecked")
   public static Builder builder() {
      return new Builder();
   }

   @Override
   public Builder toBuilder() {
      return new Builder().fromOrg(this);
   }

   public static class Builder extends Entity.Builder<Org> {

      private String fullName;

      /**
       * @see Org#getFullName()
       */
      public Builder fullName(String fullName) {
         this.fullName = fullName;
         return this;
      }

      @Override
      public Org build() {
         Org org = new Org(href, name, fullName);
         org.setDescription(description);
         org.setId(id);
         org.setType(type);
         org.setLinks(links);
         org.setTasks(tasks);
         return org;
      }

      @Override
      public Builder fromEntity(Entity<Org> in) {
         return Builder.class.cast(super.fromEntity(in));
      }

      public Builder fromOrg(Org in) {
         return fromEntity(in).fullName(in.getFullName());
      }
   }

   private Org() {
      // For JAXB and builder use
   }

   private Org(URI href, String name, String fullName) {
      super(href, name);
      this.fullName = fullName;
   }

   @XmlElement(namespace = NS, name = "FullName")
   private String fullName;

   /**
    * 
    * @return fullName of the org
    */
   public String getFullName() {
      return fullName;
   }

   @Override
   public boolean equals(Object o) {
      if (!super.equals(o))
         return false;
      Org that = Org.class.cast(o);
      return super.equals(that) && equal(fullName, that.fullName);
   }

   @Override
   public int hashCode() {
      return super.hashCode() + Objects.hashCode(fullName);
   }

   @Override
   public ToStringHelper string() {
      return super.string().add("fullName", fullName);
   }
}
