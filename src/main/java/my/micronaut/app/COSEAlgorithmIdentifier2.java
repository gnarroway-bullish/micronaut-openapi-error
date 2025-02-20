// Copyright (c) 2018, Yubico AB
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package my.micronaut.app;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * A number identifying a cryptographic algorithm. The algorithm identifiers SHOULD be values
 * registered in the IANA COSE Algorithms registry, for instance, -7 for "ES256" and -257 for
 * "RS256".
 *
 * @see <a
 *     href="https://www.w3.org/TR/2021/REC-webauthn-2-20210408/#typedefdef-cosealgorithmidentifier">§5.10.5.
 *     Cryptographic Algorithm Identifier (typedef COSEAlgorithmIdentifier)</a>
 */
public enum COSEAlgorithmIdentifier2 {
  @JsonProperty("-8")
  EdDSA(-8),
  @JsonProperty("-65535")
  RS1(-65535);

  private final long id;

  @JsonValue
  public long getId() {
    return id;
  }
  COSEAlgorithmIdentifier2(long id) {
    this.id = id;
  }

  /**
   * Attempt to parse an integer as a {@link COSEAlgorithmIdentifier2}.
   *
   * @param id an integer equal to the {@link #getId() id} of a constant in {@link
   *     COSEAlgorithmIdentifier2}
   * @return The {@link COSEAlgorithmIdentifier2} instance whose {@link #getId() id} equals <code>id
   *     </code>, if any.
   * @see <a href="https://www.w3.org/TR/2021/REC-webauthn-2-20210408/#sctn-alg-identifier">§5.8.5.
   *     Cryptographic Algorithm Identifier (typedef COSEAlgorithmIdentifier)</a>
   */
  public static Optional<COSEAlgorithmIdentifier2> fromId(long id) {
    return Stream.of(values()).filter(v -> v.id == id).findAny();
  }

  @JsonCreator
  private static COSEAlgorithmIdentifier2 fromJson(long id) {
    return fromId(id)
        .orElseThrow(
            () -> new IllegalArgumentException("Unknown COSE algorithm identifier: " + id));
  }
}
