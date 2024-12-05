package crackCodeInterview;


public class ChapterNine_SystemDesign_Scalability {

    /*
    Handling the Questions
    • Communicate: A key goal of sys�em design questions is to evaluate your ability to communicate. Stay
        engaged with the interviewer. Ask them questions. Be open about the issues of your system.
    • Go broad first: Don't dive straight into the algorithm part or get excessively focused on one part.
    Use the whiteboard: Using a whiteboard helps your interviewer follow your proposed design. Get up to
        the whiteboard in the very beginning and use it to draw a picture of what you're proposing.
    Acknowledge interviewer concerns: Your interviewer will likely jump in with concerns. Don't brush
        them off; validate them. Acknowledge the issues your interviewer points out and make changes accordingly.
    • Be careful about assumptions: An incorrect assumption can dramatically change the problem. For example, if your
        system produces analytics / statistics for a dataset, it matters whether those analytics must be totally up to date.
    • State your assumptions explicitly: When you do make assumptions, state them.This allows your interviewer to
        correct you if you're mistaken, and shows that you at least know what assumptions you're making.
      Estimate when necessary: In many cases, you might not have the data you need. For example, if you're
        designing a web crawler, you might need to estimate how much space it will take to store all the URLs.
        You can estimate this with other data you know.
    Drive: As the candidate, you should stay in the driver's seat. This doesn't mean you don't talk to your interviewer;
        in fact you must talk to your interviewer. However, you should be driving through the question. Ask questions.
        Be open about tradeoffs. Continue to go deeper. Continue to make improvements.
    These questions are largely about the process rather than the ultimate design.

Key concepts:
    Horizontal vs Vertical Scaling
    Load Balancer: cloned servers should have same data access in back end.
    Database Denormalization and NoSQL
    Database Partitioning(sharding) Vertical(partition by feature), Key-based, Directory-based
    Caching
    Async processing & Queues
    Networking metrics: Bandwidth, Throughput, Latency
    MapReduce: map step, reduce step prduces, key,value, enables parallel processing
Design Considerations:
    Fault Tolerance
    Availability & Reliability
    Read heavy vs Write heavy
    Security
     */

    public static void main(String args[]) {
        ChapterNine_SystemDesign_Scalability c4=new ChapterNine_SystemDesign_Scalability();

    /* Online analytical processing (OLAP) https://aws.amazon.com/what-is/olap/ Snowflake, RedShift
        terms:AMAZON RDS, Aurora, DynamoDB, S3CloudWatch, RedShift
        https://digitalcloud.training/aws-aurora-vs-rds-choosing-the-right-database-service-for-your-needs/
        RDS default single AZ zone, suited for low-performance applications that don’t require high scalability or responsiveness
        Aurora Replicates data across region AZs for high availability. Best for critical enterprise systems requiring continuous availability.
            Supports multi-region replication with no manual intervention in Aurora serverless, ideal for heavy-duty e-commerce and analytics platforms.
        Data Lake & Data warehouse  S3, RedShift
        https://airbyte.com/data-engineering-resources/amazon-s3-vs-dynamodb
        Amazon S3 delivers high throughput with low latency for some of its storage classes. It is suitable for applications
        that manage large volumes of data. DynamoDB is also designed for fast, high-performance applications with low
        latency requirements, especially for smaller items under 4KB.

    Database sharding vs Partitioning (https://medium.com/@_amanarora/partitioning-sharding-choosing-the-right-scaling-method-dbc6b2bec1d5)
        https://www.baeldung.com/cs/sharding
        Database partitioning involves subdividing our database entities (tables, indexes) into smaller entities within the
        same physical machine. After partitioning, operations like data loads, index creation/rebuilding, backup/recovery,
        etc., can be done at the partition level instead of the complete table reducing time for all of these operations.
        In sharding, tables are also divided into smaller tables similar to partitioning, but instead of having the data
        on the same physical machine, the data is spread across multiple server instances.
        Both approaches can be combined but adds complexity to your database architecture and require careful planning and management.
    Client Side vs Server Side Sharding
        Client-side database sharding refers to a method of distributing data across multiple databases on the client side,
        meaning that the client application is responsible for directing queries to the appropriate database.
        Server-side database sharding is a method of distributing data across multiple databases on the server side, meaning
        that the server infrastructure is responsible for directing queries to the appropriate database.

    ShardingSphere (https://www.baeldung.com/java-shardingsphere):
        An ecosystem for transforming any database into a distributed database system.
        ShardingSphere provides a mechanism for managing distributed transactions, ensuring data consistency across all databases involved.
        It also acts as a DB gateway abstracting the complexities of multiple databases into a unified data interface for the application.
        This allows developers to interact with various databases as if they were a single entity, simplifying database management.
        Supports: Data Masking, Shadowing
    Vitess:
        Vitess is a tool for MySQL that add sharding logic to your application and automatically optimises
        queries to improve database performance. It is a low-cost and efficient distributed solution for relational databases
        It doesn't support transactions across shards by default. It has a 2PC mechanism, but comes with significant performance impact
    Galera Cluster (https://galeracluster.com/library/documentation/technical-description.html)
        Galera Cluster is a synchronous certification-based replication solution for MySQL, MariaDB and Percona XtraDB.
    Eventually Consistent heuristics LWW and CRDT (https://www.yugabyte.com/blog/how-does-consensus-based-replication-work-in-distributed-databases/)
    Depending on the consistency level configured, the replica taking the write will decide whether to update other
    replicas synchronously or asynchronously. The challenge with this approach is that concurrent writes on the same
    record at two different replicas are considered perfectly valid and the final value has to determined
    non-deterministically using heuristics such as Last-Writer-Wins (LWW) and Conflict Free Replicated Data Types (CRDT).
    Such systems are considered eventually consistent (since replicas may not agree on the final value) and are prone to data loss upon failures.

    Consensus Algorithms Raft & Paxos
    9.1 Stock Data: Imagine you are building some sort of service that will be called by up to 1,000 client
    applications to get simple end-of-day stock price information (open, close, high, low). You may
    assume that you already have the data, and you can store it in any format you wish. How would you
    design the client-facing service that provides the information to client applications?You are responsible for the
    * development, rollout, and ongoing monitoring and maintenance of the feed.
    * Describe the different methods you considered and why you would recommend your approach. Your service
    can use any technologies you wish, and can distribute the information to the client applications in
    any mechanism you choose.

    9.2 Social Network: How would you design the data structures for a very large social network like Facebook or LinkedIn?
    * Describe how you would design an algorithm to show the shortest path between two people
    * (e.g., Me -> Bob -> Susan -> Jason -> You).

    9.3 Web Crawler: If you were designing a web crawler, how would you avoid getting into infinite loops?

    9.4 Duplicate URLs: You have 10 billion URLs. How do you detect the duplicate documents? In this
    case, assume "duplicate"means that the URLs are identical.

    9.5 Cache: Imagine a web server for a simplified search engine. This system has 100 machines to
    respond to search queries, which may then call out using processSearch (string query) to
    another cluster of machines to actually get the result.The machine which responds to a given query
    is chosen at random, so you cannot guarantee that the same machine will always respond to the
    same request. The method processSearch is very expensive. Design a caching mechanism for
    the most recent queries. Be sure to explain how you would update the cache when data changes.

    9.6 Sales Rank: A large eCommerce company wishes to list the best-selling products, overall and by
    category. For example, one product might be the #1056th best-selling product overall but the #13th
    best-selling product under "Sports Equipment" and the #24th best-selling product under "Safety."
    Describe how you would design this system.

    9.7 Personal Financial Manager: Explain how you would design a personal financial manager (like
    Mint.com). This system would connect to your bank accounts, analyze your spending habits, and
    make recommendations.

    9.8 Pastebin: Design a system like Pastebin, where a user can enter a piece of text and get a randomly
    generated URL to access it.
     */
    }
}



