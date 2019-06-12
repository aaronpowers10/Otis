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

public class SandwichParser implements Parser{

	private Parser outer;
	private Parser inner;
	
	public SandwichParser(Parser inner, Parser outer){
		this.outer = outer;
		this.inner = inner;
	}
	@Override
	public String parse(InputSequence in) throws CannotParseException {
		try{
			return outer.parse(in);
		} catch (CannotParseException e){
			return inner.parse(in) + outer.parse(in);
		}
	}

}
