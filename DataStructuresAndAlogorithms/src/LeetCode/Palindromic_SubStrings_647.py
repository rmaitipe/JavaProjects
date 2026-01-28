class Solution:
    def countSubstrings(self, s: str) -> int:
        #expand from each index
        # plus 2 set expand
        k= len(s)
        retval =[]
        count=0
        for i in range (k):
            j=0
            while i-j>=0 and i+j<k:
                if s[i-j]==s[i+j]:
                    count=count+1
                j=j+1
            l=0
            while i-l>=0 and i+1+l<k and s[i]==s[i+1]: 
                if s[i-l]==s[i+1+l]:
                    count=count+1
                l=l+1
        return count

greet = Solution()
print(greet.countSubstrings("malayalam"))
