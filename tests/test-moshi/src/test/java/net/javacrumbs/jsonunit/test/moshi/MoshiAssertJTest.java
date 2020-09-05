/**
 * Copyright 2009-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.javacrumbs.jsonunit.test.moshi;

import net.javacrumbs.jsonunit.test.base.AbstractAssertJTest;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static net.javacrumbs.jsonunit.test.base.JsonTestUtils.readByMoshi;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoshiAssertJTest extends AbstractAssertJTest {

    @Override
    protected Object readValue(String value) {
            return readByMoshi(value);
        }

    @Test
    void shouldAssertInteger() {
        assertThatThrownBy(() -> assertThatJson("{\"a\":1}").node("a").isIntegralNumber())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void shouldAssertIntegerFailure() {
        assertThatThrownBy(() -> assertThatJson("{\"a\":1.0}").node("a").isIntegralNumber())
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @Override
    protected void testNotEqualTo() {
        assertThatThrownBy(() -> assertThatJson("{\"test\":1}").isNotEqualTo("{\"test\": \"${json-unit.any-number}\"}"))
            .hasMessage("\n" +
                "Expecting:\n" +
                " <[{\"test\": 1}]>\n" + //FIXME
                "not to be equal to:\n" +
                " <\"{\"test\": \"${json-unit.any-number}\"}\">\n" +
                "when comparing values using JsonComparator");

    }

    @Test
    @Override
    protected void shouldEqualNumberInObject() {
        // ignored, no support of object serialization neither
    }
}
