import { useState } from 'react';
import Head from 'next/head';
import Layout from '../../components/layout';

export default function Countries() {
    console.log("Rendering /swr/counter");
    
    const [ count, setCount ] = useState(0);
    
    return (
        <Layout>
            <Head>
                <title>Counter</title>
            </Head>
            <h1>Count = {count}</h1>
            <button onClick={() => {
                setCount(count - 1)
            }}>-</button>
            <button onClick={() => {
                setCount(count + 1)
            }}>+</button>
        </Layout>
    );
}