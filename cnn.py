def train_CNN_cross_val_predict(train_data, labels, num_folds=10):
	kf = KFold(n_splits=4, shuffle=True)
	kf.get_n_splits(train_data)
	cv_acc = np.zeros((4, 1))
	fold = -1

	for train_index, test_index in kf.split(train_data):
    	X_train, X_test = train_data[train_index], train_data[test_index]
    	y_train, y_test = labels[train_index], labels[test_index]
    	input_shape = np.array([X_train.shape[1], X_train.shape[2], X_train.shape[3]])
    	fold += 1 
    	model = CNN_model(input_shape) 
    	sgd = optimizers.SGD(lr=0.001,momentum=0.9)
    	model.compile(loss=categorical_crossentropy, optimizer=sgd, metrics=["accuracy"])
    	history = model.fit(X_train, y_train, batch_size=64,epochs=100)

    	score = model.evaluate(X_test, y_test)
    	cv_acc[fold, :] = score[1]*100    
	return cv_acc

def CNN_model(input_shape):
	model = Sequential()
	model.add(Conv2D(2*8, kernel_size=(8, 1),
                 	input_shape=(input_shape[0], input_shape[1], input_shape[2]),
                 	padding="valid", kernel_regularizer=regularizers.l2(0.0001),
                 	kernel_initializer=initializers.RandomNormal(mean=0.0, stddev=0.01)))
	model.add(BatchNormalization())
	model.add(Activation('relu'))
	model.add(Dropout(0.25))  
	model.add(Conv2D(2*8, kernel_size=(1, 10),
                 	kernel_regularizer=regularizers.l2(0.0001), padding="valid"))
	model.add(BatchNormalization())
	model.add(Activation('relu'))
	model.add(Dropout(0.25))  
	model.add(Flatten())
	model.add(Dense(12, activation='softmax',
                	kernel_regularizer=regularizers.l2(0.0001),
                	kernel_initializer=initializers.RandomNormal(mean=0.0, stddev=0.01, seed=None)))
	model.summary()
	return model
