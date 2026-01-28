class Solution:
    def canJump(self, nums: List[int]) -> bool:
        retval=[]
        k=len(nums)
        for i in range (k):
            retval.append(0)
        retval[0]=1    
        for i in range (k):
            if retval[i] == 1:
                val=nums[i]
                j=i+1
                while j<i+1+val and j<k:
                    retval[j]=1
                    j=j+1
        return retval[k-1]==1




        
