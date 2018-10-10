import com.jiuzhang.model.TreeNode;

import java.util.*;
import java.util.regex.Pattern;

public class Amazon {
	/**
	 * @param s: A string
	 * @return: whether the string is a valid parentheses
	 */
	public boolean isValidParentheses(String s) {
		// write your code here
		HashMap<Character, Character> matchMap = new HashMap<>();
		matchMap.put(')', '(');
		matchMap.put(']', '[');
		matchMap.put('}', '{');
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty() || matchMap.get(s.charAt(i)) != stack.peek()) {
				stack.push(s.charAt(i));
			} else {
				stack.pop();
			}
		}
		return stack.isEmpty();
	}

	public int countPrimeSetBits(int L, int R) {
		int ans = 0;
		for (int x = L; x <= R; ++x)
			if (isSmallPrime(Integer.bitCount(x)))
				ans++;
		return ans;
	}

	public boolean isSmallPrime(int x) {
		return (x == 2 || x == 3 || x == 5 || x == 7 ||
				x == 11 || x == 13 || x == 17 || x == 19);
	}

	/**
	 * @param n: n pairs
	 * @return: All combinations of well-formed parentheses
	 */
	public List<String> generateParenthesis(int n) {
		// write your code here
		List<String> resultSet = new ArrayList<>();

		if (n == 0) {
			return resultSet;
		}

		parenthesisDfs(resultSet, "", n, n);
		return resultSet;
	}

	private void parenthesisDfs(List<String> resultSet, String parent, int left, int right) {
		if (left == 0 && right == 0) {
			resultSet.add(parent);
		}

		if (left > 0) {
			parenthesisDfs(resultSet, parent + "(", left - 1, right);
		}
		if (right > 0 && right > left) {
			parenthesisDfs(resultSet, parent + ")", left, right - 1);
		}

	}

	public String[] logSort(String[] logs) {
		if (logs == null || logs.length == 0) {
			return null;
		}
		List<List<String>> numbersLog = new ArrayList<>();
		List<List<String>> parsedLogs = new ArrayList<>();
		for (int i = 0; i < logs.length; i++) {
			String[] tempString = logs[i].split(" ", 2);
			if (stringContainsNumber(tempString[1])) {
				numbersLog.add(Arrays.asList(tempString));
			} else {
				parsedLogs.add(Arrays.asList(tempString));
			}

			Collections.sort(parsedLogs, new Comparator<List<String>>() {
				@Override
				public int compare(List<String> o1, List<String> o2) {
					//compare index 1 first
					int compareResult = compare(o1.get(1), o2.get(1));
					if (compareResult == 0) {
						// System.out.println(o1.get(0) + " " + o2.get(0));
						compareResult = o1.get(0).compareTo(o2.get(0));
					}
					return compareResult;
				}

				private int compare(String s1, String s2) {
					return s1.compareTo(s2);
				}
			});
		}
		//combine two array together
		parsedLogs.addAll(numbersLog);
		//change the string[] to string
		String[] result = new String[logs.length];
		for (int i = 0; i < logs.length; i++) {
			result[i] = parsedLogs.get(i).get(0) + " " + parsedLogs.get(i).get(1);
		}
		return result;
	}


	public boolean stringContainsNumber(String s) {
		return Pattern.compile("[0-9]").matcher(s).find();
	}

	/**
	 * @param root: the root of binary tree
	 * @return: the maximum weight node
	 */
	public TreeNode findSubtree(TreeNode root) {
		// write your code here
		ResultType result = getMaxSubtree(root);
		return result.node;
	}

	private ResultType getMaxSubtree(TreeNode node) {
		if (node == null) {
			return new ResultType(node, 0);
		}
		ResultType left = getMaxSubtree(node.left);
		ResultType right = getMaxSubtree(node.right);
		int sum = left.sum + right.sum + node.val;
		ResultType result = new ResultType(node, sum);
		//result.sum = sum;
		if (sum > left.max && sum > right.max) {
			//result.node = node;
			result.max = sum;
		} else if (left.max > right.max) {
			result.node = left.node;
			result.max = left.max;
		} else {
			result.node = right.node;
			result.max = right.max;
		}
		return result;
	}

	/**
	 * @param head: The head of linked list with a random pointer.
	 * @return: A new head of a deep copy of the list.
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		// write your code here

		if (head == null) {
			return null;
		}

		RandomListNode dummy = new RandomListNode(-1);
		RandomListNode prep, newNode;
		prep = dummy;
		Map<RandomListNode, RandomListNode> hashMap = new HashMap<>();
		while (head != null) {
			if (hashMap.containsKey(head)) {
				newNode = hashMap.get(head);
			} else {
				newNode = new RandomListNode(head.label);
				hashMap.put(head, newNode);
			}
			prep.next = newNode;
			//get randome
			if (head.random != null) {
				if (hashMap.containsKey(head.random)) {
					newNode.random = hashMap.get(head.random);
				} else {
					newNode.random = new RandomListNode(head.random.label);
					hashMap.put(head.random, newNode.random);
				}
			}
			prep = prep.next;
			head = head.next;
		}
		return dummy.next;
	}

	//How to implement it in O(1)

	class ResultType {
		TreeNode node;
		int max = Integer.MIN_VALUE;
		int sum;

		public ResultType(TreeNode node, int sum) {
			this.sum = sum;
			this.node = node;
		}
	}

	private class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	}


    private class Coordinate {
        int x,  y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate(List<Integer> res) {
            this.x = res.get(0);
            this.y = res.get(1);
        }

        public int distanceToOriginal() {
            return Math.abs(this.x) + Math.abs(this.y);
        }
    }




    private class CoordinateComparator implements Comparator<Coordinate>{
        @Override
        public int compare(Coordinate o1, Coordinate o2) {
            return o1.distanceToOriginal() - o2.distanceToOriginal();
        }
    }
    /**
     * @param restaurant:
     * @param n:
     * @return: nothing
     */
    public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
        // Write your code here
        //if n >= restaurants -> return whole list
        if(restaurant == null || n > restaurant.size()) {
            return null;
        }

        if(n == restaurant.size() ){
            return restaurant;
        }

        //minQueue -> smallest in the first

        Queue<Coordinate> sortedRest = new PriorityQueue(new CoordinateComparator());
        for(List<Integer> res : restaurant) {
            Coordinate c = new Coordinate(res);
            sortedRest.offer(c);
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0 ; i < n; i++) {
            Coordinate c = sortedRest.poll();
            List temp = new ArrayList();
            temp.add(c.x);
            temp.add(c.y);
            result.add(temp);
        }

        return result;
    }

    /**
     * @param restaurant:
     * @param n:
     * @return: nothing
     */
    public List<List<Integer>> nearestRestaurantII(List<List<Integer>> restaurant, int n) {
        // Write your code here
        //if n >= restaurants -> return whole list

        if(restaurant == null || n > restaurant.size()) {
            return null;
        }


        if(n == restaurant.size() ){
            return restaurant;
        }


        //minQueue -> smallest in the first

        //generate the coordinate list

        int[] distance = new int[restaurant.size()];
        for(int i = 0; i < distance.length; i++) {
            Coordinate coor = new Coordinate(restaurant.get(i));
            distance[i] = coor.distanceToOriginal();
        }
        Arrays.sort(distance);
        //get the distance for given dnumber
        int maxDis = distance[n-1];
        for(int dis : distance) {
            System.out.println(dis);
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0 ; i < n; i++) {
            Coordinate coor = new Coordinate(restaurant.get(i));
            if(coor.distanceToOriginal() <= maxDis) {
                result.add(restaurant.get(i));
            }
        }

        return result;
    }

    /**
     * @param s: a string
     * @param excludewords: a dict
     * @return: the most frequent word
     */
    public String frequentWord(String s, Set<String> excludewords) {
        // Write your code here
        if(s == null || s.isEmpty()) {
            return null;
        }

        //split string
        Map<String, Integer> stringMap = new HashMap<>();
        String[] subString = s.split(" ");
        int maxTime = 0;
        String maxString = "";
        //formate string
        for(int i = 0; i < subString.length; i++) {
            subString[i] = cleanString(subString[i]);
            if(excludewords.contains(subString[i])) {
                continue;
            }
            stringMap.putIfAbsent(subString[i], 0);
            stringMap.put(subString[i], stringMap.get(subString[i]) + 1);
            if(stringMap.get(subString[i]) == maxTime) {
                if(subString[i].compareTo(maxString) > 0) {
                    maxTime = stringMap.get(subString[i]);
                    maxString = subString[i];
                }
            }
            if(stringMap.get(subString[i]) > maxTime ) {
                maxTime = stringMap.get(subString[i]);
                maxString = subString[i];
            }

        }
        return maxString;
    }

    private String cleanString(String s) {
        String temp = s.toLowerCase();
        temp = temp.trim();
        char lastChar = temp.charAt(temp.length() -1);
        if(!Character.isAlphabetic(lastChar)){
            temp  = temp.substring(0, temp.length()-1);
        }
        return temp;
    }


}
