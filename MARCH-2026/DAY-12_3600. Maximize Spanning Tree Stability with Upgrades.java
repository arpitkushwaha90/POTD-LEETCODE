class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        DSU obj=new DSU(n);
        int len=edges.length;

        for(int i=0;i<len;i++)
        {
            int u=edges[i][0];
            int v=edges[i][1];
            int s=edges[i][2];
            int m=edges[i][3];

            if(m==1)
            {
                if(obj.find(u)==obj.find(v))
                {
                    return -1;
                }
                obj.union(u,v);
            }
        }

        int res=-1;
        int l=1;
        int r=(int)2e5;

        while(l<=r)
        {
            int mid=l+(r-l)/2;

            if(check(n,k,edges,mid))
            {
                res=mid;
                l=mid+1;
            }
            else
            {
                r=mid-1;
            }
        }

        return res;
    }

    public static boolean check(int n,int k,int[][] edges,int mid)
    {
        DSU dsu=new DSU(n);
        List<List<Integer>> at=new ArrayList<>();

        for(int i=0;i<edges.length;i++)
        {
            int u=edges[i][0];
            int v=edges[i][1];
            int s=edges[i][2];
            int m=edges[i][3];

            if(m==1)
            {
                if(s<mid)
                {
                    return false;
                }
                dsu.union(u,v);
            }
            else if(s>=mid)
            {
                dsu.union(u,v);
            }
            else if(2*s>=mid)
            {
                at.add(Arrays.asList(u,v));
            }
        }

        for(int i=0;i<at.size();i++)
        {
            int u=at.get(i).get(0);
            int v=at.get(i).get(1);

            if(dsu.find(u)!=dsu.find(v))
            {
                if(k<=0)
                {
                    return false;
                }
                k--;
                dsu.union(u,v);
            }
        }

        int root=dsu.find(0);

        for(int i=0;i<n;i++)
        {
            if(dsu.find(i)!=root)
            {
                return false;
            }
        }

        return true;
    }

    static class DSU
    {
        int[] arr;
        int[] rank;

        DSU(int n)
        {
            arr=new int[n];
            rank=new int[n];

            for(int i=0;i<n;i++)
            {
                arr[i]=i;
                rank[i]=0;
            }
        }

        public int find(int x)
        {
            if(arr[x]!=x)
            {
                arr[x]=find(arr[x]);
            }
            return arr[x];
        }

        public void union(int x,int y)
        {
            int px=find(x);
            int py=find(y);

            if(px==py) return;

            if(rank[px]<rank[py])
            {
                arr[px]=py;
            }
            else if(rank[px]>rank[py])
            {
                arr[py]=px;
            }
            else
            {
                arr[py]=px;
                rank[px]++;
            }
        }
    }
}
