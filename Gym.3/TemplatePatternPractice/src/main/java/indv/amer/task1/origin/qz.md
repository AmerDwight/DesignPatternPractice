# 樣板方法特訓帖：暖一暖你的樣板思維

在這一份樣板方法特訓帖中，我會透過兩道循序漸進的題目，來讓你好好地暖一暖你的樣板思維。

## 小試身手：提取樣板方法 — 去除重複程式碼

給予以下兩個類別，程式碼的意圖被我刻意使用雜亂的命名掩飾掉了，明眼人可能一下就看出兩個類別在做什麼，但是為了鍛鍊你的萃取能力，你暫時只需要關注在程式碼本身就行。

這一題小試身手就是：**你必須套用樣板方法來完全去除兩個類別中所有重複的程式碼。**

1.  **Class1.java**

    ```java
    public class Class1 {
        public void p1(int[] u) {
            int n = u.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (u[j] > u[j + 1]) {
                        int mak = u[j];
                        u[j] = u[j + 1];
                        u[j + 1] = mak;
                    }
                }
            }
        }
    }
    
    ```

2.  **Class2.java**

    ```java
    public class Class2 {
        public void p2(int[] k) {
            int n = k.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (k[j] < k[j + 1]) {
                        int ppp = k[j];
                        k[j] = k[j + 1];
                        k[j + 1] = ppp;
                    }
                }
            }
        }
    }
    
    ```


### 思維引導

1.  這一題應不算太難，我也鼓勵你更應該從這種相較簡單的題目來練習如何遵照固定思維做設計，如此一來學習的坡度才不會太陡。當你面對較難的兩星題目時你便能夠善用這套標準的萃取流程，來征服挑戰題。
2.  首先你要先尋找  `p1`  和  `p2`  方法中的「不同與共同之處」，或也可說是「變動與不變之處」。所以第一步驟是：「**先將『變動』之處挖空**」(直接把該部分的程式碼挖空），**其餘的部分就是樣板方法**。宣告一個承載樣板方法的抽象類別，並將樣板方法提取至此抽象類別中。
3.  然後再考慮被挖空的地方來定義抽象的步驟方法。這也是子類別唯一需要實作的步驟。
    1.  **Tip**：被挖空的地方很有可能會依賴一些變數，此時可將這些變數宣告成步驟方法的參數，由樣板方法負責傳至相關參數來執行步驟。
4.  最後重構  `Class1`  和  `Class2`  ，兩者都繼承剛剛宣告出來的抽象類別，並且兩者內部應只需要實作步驟。

### 解答

1.  先將「變動之處」挖空，然後宣告一個承載樣板方法的抽象類別：

    ```java
    public abstract class Template {
        public void p(int[] k) {
            int n = k.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (______) {
                        int ppp = k[j];
                        k[j] = k[j + 1];
                        k[j + 1] = ppp;
                    }
                }
            }
        }
    }
    
    ```

2.  再將被挖空的地方宣告成抽象的步驟方法：

    1.  由於被挖空的地方是一個「條件式」，兩個不同類別的條件式實作為：`u[j] > u[j + 1]`  和`k[j] < k[j + 1]`。`u`  和  `k`  皆為整數陣列`int[]`。

    2.  兩個條件式皆回傳 boolean 值且皆依賴了兩個整數。於是我們可以如此宣告這個抽象的步驟方法：`protected abstract boolean compare(int a, int b)`  ，然後在樣板方法中呼叫這個步驟：

        ```java
        public abstract class Template {
            public void p(int[] k) {
                int n = k.length;
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        if (compare(k[j], k[j+1])) {
                            int ppp = k[j];
                            k[j] = k[j + 1];
                            k[j + 1] = ppp;
                        }
                    }
                }
            }
        }
        public abstract boolean compare(int a, int b);
        
        ```

3.  重構  `Class1`  和  `Class2`  ：

    ```java
    public class Class1 extends Template {
        @Override
        protected boolean compare(int a, int b) {
            return a > b;
        }
    }
    
    public class Class2 extends Template {
        @Override
        protected boolean compare(int a, int b) {
            return a < b;
        }
    }
    
    ```

    兩個類別之間所有重複的程式碼就都被消除掉了。


## 中試身手：萃取共同行為

這一題稍有一點點挑戰性，但也不至於太難。給予以下三個類別，你必須想辦法去除所有他們之間的重複程式碼：

1.  **SearchLongestMessage.java**

    **此類別行為**：給予多個字串，印出每個字串並回傳其中「長度最長」的字串。

    ```java
    public class SearchLongestMessage {
        public String search(String[] messages) {
            String maxLengthMessage = "";
            for (String message : messages) {
                if (message.length() > maxLengthMessage.length()) {
                    maxLengthMessage = message;
                }
                System.out.println(message);
            }
            return maxLengthMessage;
        }
    }
    
    ```

2.  **SearchEmptyMessageIndex.java**

    **此類別行為**：給予多個字串，印出每個字串並回傳其中「第一個遇到的空字串」的索引。

    ```java
    public class SearchEmptyMessageIndex {
        public int search(String[] messages) {
            int index = 0;
            while (index < messages.length && !messages[index].isEmpty()) {
                System.out.println(messages[index]);
                index ++;
            }
            return index >= messages.length ? -1 : index;
        }
    }
    
    
    ```

3.  **CountNumberOfWaterballs.java**

    **此類別行為**：給予多個字串，印出每個字串並回傳其中「內容為 “Waterball”」字串的數量。

    ```java
    public class CountNumberOfWaterballs {
        public int count(String[] messages) {
            int count = 0;
            for (String message : messages) {
                if ("Waterball".equals(message)) {
                    count ++;
                }
                System.out.println(message);
            }
            return count;
        }
    }
    
    ```


### 思維引導

1.  這一題應該會開始使你感到「有一點點挑戰性」，很快地你應該也發現了「萃取標準流程」的重要性。沒關係，我會再引導你一次。這一題中若你已「程式碼」的視角下去尋找「變動/不變」之處的話可能會有一些吃力。在許多實務情境中，行為都是很混亂不清的，我個人還是習慣以「自然語言概念/邏輯」的視角下去分析，可能就能找到切入點。

    1.  好比：

        ```java
        輸入：N 個字串
        走訪每個字串 i=0...n-1，直到走訪完所有字串:
            更新最大長度字串
            印出第 i 個字串
            回傳最大長度字串
        
        ```

        若你想要體驗一下「較理性」的萃取流程的話，不妨試試看使用自然語言來描述這三個類別的「搜尋」概念/邏輯。

2.  接著如同上一題的流程，將行為中「變動」之處挖空，你就會知道哪一部分是樣板方法，哪些部分是會變動的步驟方法。而在這一題中步驟方法的數量應該至少**有兩個**。

3.  你很快就會發現：三個類別行為中「回傳」的型別是不同的。`SearchEmptyMessageIndex`  和  `CountNumberOfWaterballs`  回傳的是「整數」，而  `SearchLongestMessage`  回傳的是「字串」。

    ![c2m10-0](https://cdn.waterballsa.tw/software-design-pattern/content/c2m10/1734359756999-c2m10-0)

    -   究竟該如何定義  `SearchTemplate`  （承載樣板方法的父類別）中  `search`  方法的回傳型別？
        -   **弱型別語言**：對弱型別語言而言，可視為  `search`  回傳的型別為  `Any`  （無須宣告型別，任何型別都行），所以在實作時沒有什麼問題。

        -   **強型別語言**：但對強型別語言的話，此時就不能偷懶了，一般來說我們會使用「泛型 (Generic Type)」來實作：

            ![c2m10-1](https://cdn.waterballsa.tw/software-design-pattern/content/c2m10/1734359757081-c2m10-1)


### 解答

1.  仔細觀察，以自然語言描述三個類別中的行為，並將變動之處以小括弧標注出來：

    1.  **SearchLongestMessage.java：**

        ```java
        走訪每個字串 i=0...n-1，直到(走訪完所有字串):
            (更新最大長度字串)
            印出第 i 個字串
        回傳(最大長度字串，預設為 null)
        
        ```

    2.  **SearchEmptyMessageIndex.java：**

        ```java
        走訪每個字串 i=0...n-1，直到(遇到空字串):
            (更新空字串索引)
            印出第 i 個字串
        回傳(空訊息索引，預設為 null)
        
        ```

    3.  **CountNumberOfWaterballs.java：**

        ```java
        走訪每個字串 i=0...n-1，直到(走訪完所有字串):
            (更新 'Waterball' 字串數量)
            印出第 i 個字串
        回傳('Waterball' 字串數量，預設為 0)
        
        ```

2.  提取並調整成樣板方法：

    ```java
    走訪每個字串 i=0...n-1，直到(走訪終止條件):
        搜尋結果 = (藉由索引 i 和第 i 個字串來更新搜尋結果)
        印出第 i 個字串
    回傳（若無搜尋結果，則回傳預設值）
    
    ```

3.  實作樣板方法：

    1.  **SearchTemplate.java**

        此處  `Result`  是泛型 (Generic Type)，子類別要定義好  `Result`  的實際型別。

        樣板的實作會有一些細節需要注意：

        ```java
        public abstract class SearchTemplate<Result> {
            public Result search(String[] messages) {
                Result result = defaultSearchResult();
                for (int idx = 0; idx < messages.length; idx++) {
                    result = updateSearchResult(idx, messages[idx]);
                    System.out.println(messages[idx]);
                    if (searchEnd(idx, messages)) {
                        break;
                    }
                }
                return result;
            }
        
            protected Result defaultSearchResult() {
                return null; // Hook，如果傳入空陣列的話會回傳預設結果
            }
        
            protected boolean searchEnd(int messageIndex, String[] messages) {
                return false; // Hook，預設是直到走訪玩迴圈才結束
            }
        
            protected abstract Result updateSearchResult(int messageIndex, String message);
        }
        
        ```

    2.  **SearchLongestMessage.java**

        ```java
        public class SearchLongestMessage extends SearchTemplate<String> {
            private String maxLengthMessage = "";
            @Override
            protected String updateSearchResult(int messageIndex, String message) {
                maxLengthMessage = message.length() > maxLengthMessage.length() ? message : maxLengthMessage;
                return maxLengthMessage;
            }
        }
        
        ```

    3.  **SearchEmptyMessageIndex.java**

        ```java
        public class SearchEmptyMessageIndex extends SearchTemplate<Integer> {
            @Override
            protected boolean searchEnd(int messageIndex, String[] messages) {
                return messages[messageIndex].isEmpty();
            }
        
            @Override
            protected Integer updateSearchResult(int messageIndex, String message) {
                return message.isEmpty() ? messageIndex : -1;
            }
        }
        
        ```

    4.  **CountNumberOfWaterball.java**

        ```java
        public class CountNumberOfWaterball extends SearchTemplate<Integer> {
            private int count = 0;
        
            @Override
            protected Integer defaultSearchResult() {
                return 0;
            }
        
            @Override
            protected Integer updateSearchResult(int messageIndex, String message) {
                if ("Waterball".equals(message)) {
                    count ++;
                }
                return count;
            }
        }
        
        ```


這樣就去除了所有類別之間的重複程式碼囉。

### 特訓結語

好啦，幫你暖好身了，你準備好要迎接道館挑戰了嗎？相信你自己，勇於地做 OOA/D、不要被需求給嚇到了！走吧！