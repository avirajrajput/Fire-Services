package com.manacher.firestoreservice;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FireStoreService {

    private FirebaseFirestore fireStore;

    public FireStoreService() {
        fireStore = FirebaseFirestore.getInstance();
        //show toast something went wrong
    }

    public Task<DocumentSnapshot> getData(String collection, String document){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        return documentReference.get();
    }

    public Task<DocumentSnapshot> getDataSubCollection(String collection, String document, String subCollection, String subDocument){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        CollectionReference subCollectionReference = documentReference.collection(subCollection);
        DocumentReference subDocumentReference = subCollectionReference.document(subDocument);

        return subDocumentReference.get();
    }

    public Query getSubCollectionRef(String collection, String document, String subCollection){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        return documentReference.collection(subCollection);
    }

    public DocumentReference getSubCollectionSubDocumentRef(String collection, String document, String subCollection, String subDocument){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        CollectionReference collectionReference2 = documentReference.collection(subCollection);

        return collectionReference.document(subDocument);
    }

    public CollectionReference getSubCollection(String collection, String document, String subCollection){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        return documentReference.collection(subCollection);
    }

    public Task<Void> deleteSubCollectionSubDocument(String collection, String document, String subCollection, String subDoc){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        CollectionReference subCollectionReference = documentReference.collection(subCollection);
        DocumentReference subDocumentReference = subCollectionReference.document(subDoc);

        return subDocumentReference.delete();
    }

    public Task<Void> setData(String collection, String document, Object object){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        return documentReference.set(object);
    }

    public Task<DocumentReference> pushData(String collection, Object object){

        CollectionReference collectionReference = fireStore.collection(collection);
//        DocumentReference documentReference = collectionReference.document(document);

        return collectionReference.add(object);
    }

    public Task<Void> setDataSubCollection(String collection, String document, String subCollection, String subDocument, Object object){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        CollectionReference subCollectionReference = documentReference.collection(subCollection);
        DocumentReference subDocumentReference = subCollectionReference.document(subDocument);

        return subDocumentReference.set(object);
    }

    public Task<Void> deleteData(String collection, String document){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        return documentReference.delete();
    }

    public Task<Void> updateData(String collection, String document, Map<String, Object> map){

        CollectionReference collectionReference = fireStore.collection(collection);
        DocumentReference documentReference = collectionReference.document(document);

        return documentReference.update(map);
    }

    public Task<QuerySnapshot> getWhereIn(String collection, String field, List<String> list){
        return fireStore.collection(collection).whereIn(field, list).get();

    }

    public Task<QuerySnapshot> getWhichContains(String collection, String field, Object object){

        return fireStore.collection(collection).whereArrayContains(field, object).get();
    }

    public Task<QuerySnapshot> getWhichEquals(String collection, String field, Object object){

        return fireStore.collection(collection).whereEqualTo(field, object).get();
    }

    public DocumentReference getDocReference(String collection, String document){
        return fireStore.collection(collection).document(document);

    }
    public Task<QuerySnapshot> getOrderedList(String collection, String orderByField, int limit){
        return fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING)
                .limit(limit)
                .get();
    }

    public Task<QuerySnapshot> getOrderedWhereEqual(String collection, String equalField, Object object, String orderByField){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .whereEqualTo(equalField, object).get();

    }

    public Task<QuerySnapshot> getOrderedWhereEqual(String collection, String equalField, Object object, String orderByField, int limit){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .limit(limit)
                .whereEqualTo(equalField, object).get();

    }

    public Task<QuerySnapshot> getOrderedWhereEqual(String collection, String equalField_1, String object_1, String equalField_2, Object object_2, String orderByField){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .whereEqualTo(equalField_1, object_1)
                .whereEqualTo(equalField_2, object_2)
                .get();

    }

    public Task<QuerySnapshot> getListByListWhereEqual(String collection, String equalField_1, List<String> object_1, String equalField_2, Object object_2, String orderByField){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .whereIn(equalField_1, object_1)
                .whereEqualTo(equalField_2, object_2)
                .get();

    }

    public Task<QuerySnapshot> getOrderedWhereEqual(String collection, String equalField_1, Object object_1, String equalField_2, Object object_2, String orderByField, int limit){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .whereEqualTo(equalField_1, object_1)
                .whereEqualTo(equalField_2, object_2)
                .limit(limit)
                .get();

    }

    public Task<QuerySnapshot> getOrderedWhereContain(String collection, String equalField_1, Object object_1, String equalField_2, Object object_2, String orderByField, int limit){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .whereEqualTo(equalField_1, object_1)
                .whereArrayContains(equalField_2, object_2)
                .limit(limit)
                .get();

    }

    public Task<QuerySnapshot> getOrderedWhereEqual(String collection, String equalField_1,
                                                    Object object_1, String equalField_2, Object object_2, String orderByField, int limit,
                                                    DocumentReference documentReference){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .startAfter(documentReference)
                .whereEqualTo(equalField_1, object_1)
                .whereEqualTo(equalField_2, object_2)
                .limit(limit)
                .get();

    }

    public Query getOrderedWhereEqualQuery(String collection, String equalField_1, Object object_1,
                                           String equalField_2, Object object_2, String orderByField, int limit){

        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .whereEqualTo(equalField_1, object_1)
                .limit(limit)
                .whereEqualTo(equalField_2, object_2);

    }


    public Query getOrderedWhereEqualQuery(String collection, String equalField_1,
                                                    Object object_1, String equalField_2, Object object_2,
                                           String orderByField, int limit, DocumentSnapshot  documentSnapshot){

        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .startAfter(documentSnapshot)
                .whereEqualTo(equalField_1, object_1)
                .limit(limit)
                .whereEqualTo(equalField_2, object_2);

    }

    public Query getOrderedWhereArrayContainsQuery(String collection, String equalField_1, Object object_1,
                                           String equalField_2, Object object_2, String orderByField, int limit){

        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .whereArrayContains(equalField_1, object_1)
                .limit(limit)
                .whereEqualTo(equalField_2, object_2);

    }


    public Query getOrderedWhereArrayContainsQuery(String collection, String equalField_1,
                                           Object object_1, String equalField_2, Object object_2,
                                           String orderByField, int limit, DocumentSnapshot  documentSnapshot){

        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .startAfter(documentSnapshot)
                .whereArrayContains(equalField_1, object_1)
                .limit(limit)
                .whereEqualTo(equalField_2, object_2);

    }

    public Query getOrderedWhereEqualQuery(String collection, String orderByField, int limit){

        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .limit(limit);

    }
    public Query getOrderedWhereEqualQuery(String collection, String orderByField, int limit, DocumentSnapshot  documentSnapshot){

        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING))
                .startAfter(documentSnapshot)
                .limit(limit);

    }

    public Task<QuerySnapshot> getOrderedWhereEqualAscending(String collection, String equalField, String object, String orderByField){
        return (fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.ASCENDING))
                .whereEqualTo(equalField, object).get();

    }

    public Task<QuerySnapshot> getOrderedList(String collection, String orderByField){
        return fireStore.collection(collection)
                .orderBy(orderByField, Query.Direction.DESCENDING)
                .get();
    }

    public Task<QuerySnapshot> getQueryData(String collection, Map<String, Object> queryList, String orderBy, long limit){

        CollectionReference collectionReference = fireStore.collection(collection);

        Set<String> keys = queryList.keySet();
        Query query = null;

        for (String key : keys ) {
            if(query == null){
                query = collectionReference.whereEqualTo(key, queryList.get(key));
            }else {
                query = query.whereEqualTo(key, queryList.get(key));
            }

        }
        query.orderBy(orderBy);
        query.limit(limit);
        return query.get();

    }

//    public Task<String> getToken(){
//        return FirebaseMessaging.getInstance().getToken();
//
//    }

    public void updateQueryData(final String collection, final Map<String, Object> data, Map<String, Object> queryList, final Object object){

        CollectionReference collectionReference = fireStore.collection(collection);

        Set<String> keys = queryList.keySet();

        Query query = null;

        for (String key : keys ) {
            if(query == null){
                query = collectionReference.whereEqualTo(key, queryList.get(key));
            }else {
                query = query.whereEqualTo(key, queryList.get(key));
            }

        }

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("LLKKSJJ", "onSuccess: ");
                if (queryDocumentSnapshots.getDocuments().size() == 0){
                    Log.d("LLKKSJJ", "onSuccess<inside>: ");
                    setData(collection, ""+new Date().getTime(), object)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("LLKKSJJ", "onFailure: "+e.getMessage());
                        }
                    });
                    return;
                }
                Log.d("LLKKSJJ", "onSuccess<out>: ");
                DocumentReference documentReference = queryDocumentSnapshots.getDocuments().get(0).getReference();
                documentReference.update(data);

            }}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("LLKKSJJ", "onFailure: "+e.getMessage());
            }
        });
    }
}


