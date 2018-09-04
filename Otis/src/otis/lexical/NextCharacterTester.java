/*
 *
 *  Copyright (C) 2018 Aaron Powers
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package otis.lexical;

public class NextCharacterTester {
	private StringComparer comparer;

	public NextCharacterTester(CompareType compareType) {
		if (compareType == CompareType.CASE_SENSITIVE) {
			comparer = new CaseSensitive();
		} else if (compareType == CompareType.CASE_INSENSITIVE) {
			comparer = new CaseInsensitive();
		}

	}

	public boolean isNextCharacter(String character, InputSequence in) {
		if (comparer.compare(in.token(), character)) {
			return true;
		} else {
			return false;
		}
	}

	private enum CompareType {
		CASE_SENSITIVE, CASE_INSENSITIVE;
	}

	public static CompareType CASE_SENSITIVE() {
		return CompareType.CASE_SENSITIVE;
	}

	public static CompareType CASE_INSENSITIVE() {
		return CompareType.CASE_INSENSITIVE;
	}

}
