/*
 *
 *  Copyright (C) 2017 Aaron Powers
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

import java.util.ArrayList;

public class InputSequence {

	private ArrayList<Character> chars;
	private int readIndex;
	private int maxColumn;
	private int lastColumn;
	private int lastLine;
	private MoreCharactersSource moreChars;
	
	public InputSequence(MoreCharactersSource moreChars){
		this.moreChars = moreChars;
		maxColumn = -1;
	}

	public InputSequence(int maxColumn) {
		this.maxColumn = maxColumn;
		chars = new ArrayList<Character>();
		readIndex = 0;
		lastColumn = 1;
		lastLine = 1;
		moreChars = new NullCharactersSource();
	}

	public InputSequence(String fileName, int maxColumn) {
		this.maxColumn = maxColumn;
		chars = new ArrayList<Character>();
		lastColumn = 1;
		lastLine = 1;
		readIndex = 0;
		moreChars = new FileLink(fileName);
	}
	
	public InputSequence(String fileName) {
		maxColumn = -1;
		chars = new ArrayList<Character>();
		lastColumn = 1;
		lastLine = 1;
		readIndex = 0;
		moreChars = new FileLink(fileName);
	}
	
	public void remove(int offset, int numToRemove) {
		for(int i=0;i<numToRemove;i++) {
			//System.out.println("REMOVING: " + chars.get(chars.size()-1));
			chars.remove(chars.size()-offset-1);
			readIndex--;
		}
	}

	public void addChar(String newChar) {
		if (maxColumn == -1 || lastColumn <= maxColumn || newChar.equals("\r") || newChar.equals("\n")) {
			chars.add(newChar.toCharArray()[0]);
			//chars.trimToSize();
		}

		if (newChar.equals("\n")) {
			lastColumn = 1;
			lastLine++;
		} else {
			lastColumn++;
		}
		
	}

	public void reset() {
		readIndex = 0;
	}

	protected String token() {
		while (readIndex > chars.size() - 1) {
			addChar(moreChars.nextChar());
		}
		return new String(new char[] {chars.get(readIndex)});
	}

	public int lineNumber() {
		return lastLine;
	}

	public int col() {
		return lastColumn;
	}

	protected void increment() {
		readIndex++;
	}

	protected String peek(int offset) {
		return new String(new char[] {chars.get(readIndex + offset)});
	}

	protected int pos() {
		return readIndex;
	}

	protected void moveTo(int pos) {
		this.readIndex = pos;
	}

	public int maxColumn() {
		return maxColumn;
	}
	
	public void close(){
		moreChars.close();
	}
	
	public boolean hasNext(){
		return moreChars.hasNext();
	}
	
	public int size() {
		return chars.size();
	}

	class NullCharactersSource implements MoreCharactersSource {

		@Override
		public String nextChar() {
			throw new EndOfSequenceException("The end of the sequence was reached.");
		}
		
		@Override
		public void close(){
			
		}

		@Override
		public boolean hasNext() {
			return false;
		}

	}

}
