import Head from 'next/head';
import Layout from '@/components/layout';
import { useCountries } from '@/hooks/useCountries';

export default function Countries() {
    console.log("Rendering /withContext/countries");
    const { data, error } = useCountries();
    
    if (error) return <div>failed to load</div>
    if (!data) return <div>loading...</div>
    
    return (
    <Layout>
        <Head>
            <title>All Countries</title>
        </Head>
        <h1>All Countries</h1>
        <ul>
          {data.map(({ name: { official } }) => (
            <li key={official}>
              {official}
            </li>
          ))}
        </ul>
    </Layout>
    );
}