import { React, useState, useEffect } from "react";

export default function Home() {

    const [data, setData] = useState(null);

    useEffect( () => {
        const fetchData = async () => {
            try {
                const response = await fetch("/", { cache: "no-store" });
                console.log("Response JSON:", await response.json());
            }
            catch (error) {
                console.error("Error fetching data:", error);
            }
        }
        console.log("Fetching data from API...");
        fetchData();

    }, []);

    return (
        <div>
            <div className="header">
                <h1>Hello, Welcome to My App</h1>
            </div>
            <div className="body">
                {data && <p>API Response: {data}</p>}
            </div>
        </div>
    );
}