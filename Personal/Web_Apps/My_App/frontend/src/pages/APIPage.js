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
        <div className="root">
            <div className="header">
                <h1>Welcome to The API Page</h1>
                <a className="hButton" href="/">Back Home</a>
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
            <div className="footer">
                <p>© 2025 Paul Pollard. Not really copyrighted.</p>
            </div>
        </div>
    );
}