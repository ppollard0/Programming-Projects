import { React, useState, useEffect } from "react";

export default function APIPage() {

    const [data, setData] = useState([]);

    useEffect( () => {
        const fetchData = async () => {
            try {
                const response = await fetch("/api", { cache: "no-store" });
                const json = await response.json();
                setData(json);
            }
            catch (error) {
                console.error("Error fetching data:", error);
            }
            
        }
        fetchData();
    }, []);

    return (
        <div>
            <div className="header">
                <a href="/">Back Home</a>
                <h1>Welcome to The API Page</h1>
            </div>
            <div className="body">
                <div>
                    <h3>Here you can look at the various APIs on display</h3>
                </div>
                <div className="apis">
                    {data && data.map((message, index) => (
                    <div key={index}>
                        <p>{message}</p>
                    </div>
                ))}                    
                </div>
            </div>
        </div>
    );
}